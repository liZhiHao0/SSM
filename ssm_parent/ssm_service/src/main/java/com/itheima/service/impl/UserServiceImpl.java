package com.itheima.service.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by crowndint on 2018/11/4.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        //判断字符串是否为null以及长度是否为0，并做了异常处理
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {

            return null;
        }

        return userDao.findUserByUsernameAndPassword(username, password);
    }




}
