package com.lois.dao;

import com.lois.domain.Account;
import com.lois.domain.AccountUser;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有账户，同时获取账户所有人及年龄
     * @return
     */
    public List<Account> findAll();
}
