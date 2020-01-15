package com.wugou.service;

import com.wugou.dao.UserDao;
import com.wugou.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    //利用set进行动态实现值的注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
