import request from '@/utils/request'
// 查询菜单列表
export function listMenu(query) {
  return request({
    url: '/system/menu/list',
    method: 'get',
    params: query
  })
}
// 新增菜单
export function addMenu(data) {
  return request({
    url: '/system/menu',
    method: 'post',
    data: data
  })
}
// 查询菜单详细数据
export function getMenu(menuId) {
  return request({
    url: '/system/menu/' + menuId,
    method: 'get'
  })
}
// 修改菜单
export function updateMenu(data) {
  return request({
    url: '/system/menu',
    method: 'put',
    data: data
  })
}
// 删除菜单数据
export function delMenu(menuId) {
  return request({
    url: '/system/menu/' + menuId,
    method: 'delete'
  })
}
