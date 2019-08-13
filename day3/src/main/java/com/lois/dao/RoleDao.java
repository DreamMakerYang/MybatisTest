package com.lois.dao;

import com.lois.domain.Role;

import java.util.List;

public interface RoleDao {
    /**
     * 查询所有角色，以及拥有角色的用户
     * @return
     */
    public List<Role> findAll();
}
