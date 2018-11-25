package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    //注入编码器
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    //认证
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserInfo userInfo=null;
        try {
            userInfo= userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user=new User(userInfo.getUsername(),userInfo.getPassword(), getAuthority(userInfo.getRoles()));
        return user;

    }
    //授权
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;

    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    //加密
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }
}
