package com.lois.dao;

import com.lois.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户，并且查询出用户下所有账户信息
     * @return
     */
    public List<User> findAll();
}
