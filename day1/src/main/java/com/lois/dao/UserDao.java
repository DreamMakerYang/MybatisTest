package com.lois.dao;

import com.lois.domain.QueryVo;
import com.lois.domain.User;


import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    public List<User> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User findById(int id);

    /**
     * 保存用户
     * @param user
     */
    public void save(User user);

    /**
     * 修改用户
     * @param user
     */
    public void update(User user);

    /**
     * 根据id删除用户
     * @param id
     */
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
