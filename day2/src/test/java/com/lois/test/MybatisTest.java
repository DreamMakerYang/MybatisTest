package com.lois.test;

import com.lois.dao.AccountDao;

import com.lois.dao.RoleDao;
import com.lois.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;



public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private AccountDao accountDao;
    private UserDao userDao;
    private RoleDao roleDao;
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
        System.out.println(accountDao.findAll());
    }

    @Test
    public void findAllUser(){
        System.out.println(userDao.findAll());
    }

    @Test
    public void findAllRole(){
        System.out.println(roleDao.findAll());
    }
}
