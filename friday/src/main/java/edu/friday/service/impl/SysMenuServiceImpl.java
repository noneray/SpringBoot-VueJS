package edu.friday.service.impl;

import edu.friday.common.constant.UserConstants;
import edu.friday.common.result.TreeSelect;
import edu.friday.model.SysMenu;
import edu.friday.model.vo.MetaVO;
import edu.friday.model.vo.RouterVO;
import edu.friday.model.vo.SysMenuVO;
import edu.friday.model.vo.SysUserVO;
import edu.friday.repository.SysMenuRepository;
import edu.friday.service.SysMenuService;
import edu.friday.utils.BeanUtils;
import edu.friday.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    SysMenuRepository sysMenuRepository;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = sysMenuRepository.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(Long userId) {
        return selectMenuList(new SysMenuVO(), userId);
    }

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenuVO menu, Long userId) {
        List<SysMenu> menuList;
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyPropertiesIgnoreEmpty(menu, sysMenu);
        Sort sort = Sort.by("parentId", "orderNum");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("menuName", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<SysMenu> example = Example.of(sysMenu, exampleMatcher);
        //管理员显示所有菜单信息
        if (SysUserVO.isAdmin(userId)) {
            menuList = sysMenuRepository.findAll(example, sort);
        } else {
            menuList = sysMenuRepository.selectMenuListByUserId(sysMenu, userId);
        }
        return menuList;
    }

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenuVO> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus;
        if (SysUserVO.isAdmin(userId)) {
            menus = sysMenuRepository.selectMenuTreeAll();
        } else {
            menus = sysMenuRepository.selectMenuTreeByUserId(userId);
        }
        List<SysMenuVO> menuVOS = BeanUtils.copyProperties(menus, SysMenuVO.class);
        return getChildPerms(menuVOS, 0);
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        List<Long> rs = new ArrayList<>();
        List<SysMenu> menuList = sysMenuRepository.selectMenuListByRoleId(roleId);
        for (SysMenu sysMenu : menuList) {
            rs.add(sysMenu.getMenuId());
        }
        return rs;
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysMenu selectMenuById(Long menuId) {
        Optional<SysMenu> op = sysMenuRepository.findById(menuId);
        return op.orElse(null);
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果true存在false不存在
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(menuId);
        long result = sysMenuRepository.count(Example.of(sysMenu));
        return result > 0;
    }

    /**
     * 查询菜单是否存在角色
     *
     * @param menuId 菜单ID
     * @return 结果true存在false不存在
     */
    @Override
    public boolean checkMenuExistRole(Long menuId) {
        long result = sysMenuRepository.checkMenuExistRole(menuId);
        return result > 0;
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(menuId);
        sysMenu.setMenuName(menu.getMenuName());
        Page<SysMenu> list = sysMenuRepository.findAll(Example.of(sysMenu), PageRequest.of(0, 1));
        SysMenu info = null;
        if (list.getTotalElements() > 0) {
            info = list.toList().get(0);
        }
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenu menu) {
        sysMenuRepository.save(menu);
        return null != menu.getMenuId() ? 1 : 0;
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(SysMenu menu) {
        SysMenu sysMenu = new SysMenu();
        Optional<SysMenu> op = sysMenuRepository.findById(menu.getMenuId());
        if (!op.isPresent()) {
            return 0;
        }
        sysMenu = op.get();
        BeanUtils.copyPropertiesIgnoreNull(menu, sysMenu);
        sysMenuRepository.save(sysMenu);
        return 1;
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId) {
        sysMenuRepository.deleteById(menuId);
        return 1;
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVO> buildMenus(List<SysMenuVO> menus) {
        List<RouterVO> routers = new LinkedList<>();
        for (SysMenuVO menu : menus) {
            RouterVO router = new RouterVO();
            router.setName(StringUtils.capitalize(menu.getPath()));
            router.setPath(getRouterPath(menu));
            router.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
            router.setMeta(new MetaVO(menu.getMenuName(), menu.getIcon()));
            List<SysMenuVO> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && "M".equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端所需要的树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysMenuVO> buildMenuTree(List<SysMenuVO> menus) {
        List<SysMenuVO> returnList = new ArrayList<>();
        for (SysMenuVO t : menus) {
            //根据传入的某个父节点ID，遍历该父节点的所有子节点
            if (t.getParentId() == 0) {
                recursionFn(menus, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 构建前端所需要的下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenuVO> menus) {
        List<SysMenuVO> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysMenuVO> list, SysMenuVO t) {
        //得到子节点列表
        List<SysMenuVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuVO tChild : childList) {
            if (hasChild(list, tChild)) {
                //判断是否有子节点
                for (SysMenuVO n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuVO> getChildList(List<SysMenuVO> list, SysMenuVO t) {
        List<SysMenuVO> tlist = new ArrayList<>();
        for (SysMenuVO n : list) {
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuVO> list, SysMenuVO t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuVO> getChildPerms(List<SysMenuVO> list, int parentId) {
        List<SysMenuVO> returnList = new ArrayList<>();
        for (SysMenuVO t : list) {
            //根据传入的某个父节点ID，遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenuVO menu) {
        String routerPath = menu.getPath();
        //非外链并且是一级目录
        if (0 == menu.getParentId() && "1".equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }
}
