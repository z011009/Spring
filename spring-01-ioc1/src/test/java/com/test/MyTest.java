package com.test;

import com.wugou.dao.UserDaoMysqlImpl;
import com.wugou.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void Test1() {
        //用户实际调用的是业务层，dao层他们不需要接触
        UserServiceImpl UserServiceImpl = new UserServiceImpl();

        UserServiceImpl.setUserDao(new UserDaoMysqlImpl());
        UserServiceImpl.getUser();
    }

    @Test
    public void Test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");
        userServiceImpl.getUser();
    }
}
