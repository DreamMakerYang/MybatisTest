package com.lois.dao;

import com.lois.domain.QueryVo;
import com.lois.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;


import java.util.List;

@CacheNamespace(blocking = true)
public interface UserDao {
    /**
     * 查询所有用户，并且查询出用户下所有账户信息
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "age",property = "age"),
            @Result(column = "id",property = "accounts",many = @Many(select = "com.lois.dao.AccountDao.findByUid",fetchType = FetchType.LAZY))
    })
    public List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */

    @Select("select * from user where id = #{id}")
    public User findById(int id);

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(name,age) values (#{name},#{age})")
    public void save(User user);

    /**
     * 修改用户
     * @param user
     */
    @Update("update user set name = #{name},age = #{age} where id = #{id}")
    public void update(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    public void deleteById(int id);

    /**
     * 模糊查询#{}
     * @param name
     * @return
     */
    public List<User> findLikeName1(String name);

    /**
     * 模糊查询${}
     * @param name
     * @return
     */
    public List<User> findLikeName2(String name);

    /**
     * 动态sql——if和where
     * @param user
     * @return
     */
    public List<User> findByUser1(User user);

    public List<User> findByUserInAge(QueryVo queryVo);
}
