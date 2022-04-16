package edu.friday.repository.custom.impl;

import edu.friday.repository.custom.SysRoleCustomRepository;
import edu.friday.utils.SqlUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class SysRoleCustomRepositoryImpl implements SysRoleCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int batchInsertRoleMenu(Long[] roles, Long[] menus) {
        int length = Math.min(roles.length, menus.length);
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into sys_role_menu ( role_id,menu_id ) values ");
        sql.append(SqlUtil.getBatchInsertSqlStr(length, 2));
        Query query = entityManager.createNativeQuery(sql.toString());
        int paramIndex = 1;
        for (int i = 0; i < length; i++) {
            query.setParameter(paramIndex++, menus[i]);
            query.setParameter(paramIndex++, roles[i]);
        }
        return query.executeUpdate();
    }
}
