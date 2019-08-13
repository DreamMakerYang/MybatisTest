package com.lois.test;

import com.lois.dao.AccountDao;

import com.lois.dao.RoleDao;
import com.lois.dao.UserDao;
import com.lois.domain.Account;
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
import java.util.List;


public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private AccountDao accountDao;
    private UserDao userDao;
    private RoleDao roleDao;
    private SqlSessionFactory factory;
    //在测试方法执行之前执行
    @Before
    public void before() throws IOException {
        //1.获取工厂图纸（配置信息）
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.招募工人（创建构建者）
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.给工人图纸建造工厂（构建者构建工厂对象）
        factory = builder.build(in);
        //4.构建产品流水线（打开自动提交事务）
        session = factory.openSession(true);
        //5.根据产品设计图生产出产品（动态代理）
        accountDao = session.getMapper(AccountDao.class);
        userDao = session.getMapper(UserDao.class);
        roleDao = session.getMapper(RoleDao.class);
    }
    //在测试方法执行之后执行
    @After
    public void after() throws IOException {
        //7.回收（释放资源）
        session.close();
        in.close();
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

    @Test
    public void findAllRole(){
        System.out.println(roleDao.findAll());
    }

    @Test
    public void findByIdUser(){
        System.out.println(userDao.findById(1));
    }

    @Test
    public void findByUidAccount(){
        System.out.println(accountDao.findByUid(1));
    }

    @Test
    public void testFirstLevelCache1(){
        User user1 = userDao.findById(1);
        System.out.println(user1);
        User user2 = userDao.findById(1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    @Test
    public void testFirstLevelCache2(){
        User user1 = userDao.findById(1);
        System.out.println(user1);
        //重启session
//        session.close();
//        session = factory.openSession(true);
//        userDao = session.getMapper(UserDao.class);
        session.clearCache();

        User user2 = userDao.findById(1);
        System.out.println(user2);
        System.out.println(user1 == user2);
    }

    @Test
    public void testSecondLevelCache(){
        SqlSession session1 = factory.openSession();
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user1 = userDao1.findById(1);
        System.out.println(user1);
        session1.close();//一级缓存消失

        SqlSession session2 = factory.openSession();
        UserDao userDao2 = session2.getMapper(UserDao.class);
        User user2 = userDao2.findById(1);
        System.out.println(user2);
        session2.close();//一级缓存消失

        System.out.println(user1 == user2);
    }
}
