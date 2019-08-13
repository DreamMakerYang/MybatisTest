package com.lois.test;

import com.lois.dao.AccountDao;
import com.lois.dao.UserDao;
import com.lois.domain.Account;
import com.lois.domain.QueryVo;
import com.lois.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private UserDao userDao;
    private AccountDao accountDao;
    //在测试方法执行之前执行
    @Before
    public void before() throws IOException {
        //1.获取工厂图纸（配置信息）
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.招募工人（创建构建者）
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.给工人图纸建造工厂（构建者构建工厂对象）
        SqlSessionFactory factory = builder.build(in);
        //4.构建产品流水线（打开自动提交事务）
        session = factory.openSession(true);
        //5.根据产品设计图生产出产品（动态代理）
        userDao = session.getMapper(UserDao.class);
        accountDao = session.getMapper(AccountDao.class);
    }
    //在测试方法执行之后执行
    @After
    public void after() throws IOException {
        //7.回收（释放资源）
        session.close();
        in.close();
    }

    @Test
    public void findAll(){
        //6.使用产品
        List<User> users = userDao.findAll();
        for (User user :
                users) {
            System.out.println(user);
        }
    }

    @Test
    public void findById(){
        //6.使用产品
        User user = userDao.findById(1);
        System.out.println(user);
    }

    @Test
    public void save(){
        User user = new User();
        user.setName("小明");
        user.setAge(33);
        userDao.save(user);
        //查看数据库表数据变化
    }

    @Test
    public void update(){
        //修改id为3的用户年龄
        User user = userDao.findById(5);
        System.out.println(user.getAge());
        user.setAge(100);
        userDao.update(user);
        //查看数据库表中id为3的用户年龄和控制台中打印的数字
    }

    @Test
    public void deleteById(){
        //删除id为3的用户
        userDao.deleteById(5);
    }

    @Test
    public void findAllAccount(){
        List<Account> all = accountDao.findAll();
        for (Account account :
                all) {
            System.out.println("---------------------");
            System.out.println(account);
        }
    }

    @Test
    public void findAllUser(){
        List<User> all = userDao.findAll();
        for (User user :
                all) {
            System.out.println("------------------------");
            System.out.println(user);
        }
    }
}
