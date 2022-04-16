package edu.friday.repository.custom;

import org.springframework.stereotype.Repository;

@Repository
public interface SysUserCustomRepository {
    int batchInsertUserRole(Long[] userIds, Long[] roles);
}
