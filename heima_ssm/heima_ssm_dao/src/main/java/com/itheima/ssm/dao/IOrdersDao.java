package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(property = "product",column = "productId",one=@One(select = "com.itheima.ssm.dao.IProductDao.findById"))
    })
     List<Orders> findAll();


    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "product",column = "productId",one=@One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",one=@One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId);
}
