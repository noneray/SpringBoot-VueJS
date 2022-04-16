package edu.friday.repository.custom;

public interface SysRoleCustomRepository {
    int batchInsertRoleMenu(Long[] roles, Long[] menus);
}