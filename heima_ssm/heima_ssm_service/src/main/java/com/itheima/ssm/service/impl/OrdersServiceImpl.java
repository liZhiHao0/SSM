package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
