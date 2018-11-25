package com.itheima.utils;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("user");
        if(user!=null) {

            //放行
            return true;
        }

        //用户名为null,进行拦截
        //response.sendRedirect(request.getContextPath()+"/login.jsp");
        request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request, response);

        return false;
    }

}
