package edu.friday.repository.custom.impl;

import edu.friday.repository.custom.SysUserCustomRepository;
import edu.friday.utils.SqlUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class SysUserCustomRepositoryImpl implements SysUserCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int batchInsertUserRole(Long[] userIds, Long[] roles) {
        int length = Math.min(userIds.length, roles.length);
        String sql = "insert into  sys_user_role(user_id,role_id) values " +
                SqlUtil.getBatchInsertSqlStr(length, 2);
        Query query = entityManager.createNativeQuery(sql);
        int paramIndex = 1;
        for (int i = 0; i < length; i++) {
            query.setParameter(paramIndex++, userIds[i]);
            query.setParameter(paramIndex++, roles[i]);
        }
        return query.executeUpdate();
    }
}
