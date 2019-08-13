package com.lois.dao;

import com.lois.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有账户，同时获取账户所有人及年龄
     * @return
     */
    @Select("select * from account")
    @Results(id = "AccountMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(column = "uid",property = "user",one = @One(select = "com.lois.dao.UserDao.findById",fetchType = FetchType.LAZY))
    })
    public List<Account> findAll();

    @Select("select * from account where uid = #{uid}")
    public List<Account> findByUid(int uid);
}
