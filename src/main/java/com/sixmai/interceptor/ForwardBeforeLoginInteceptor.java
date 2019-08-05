package com.sixmai.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  * 用户登录以后跳转回之前页面的拦截器
 * 拦截对象：
 * 除登录，注册之外的所有跳转页面的请求
 * 因为用户随时可能进行登录操作
 * Created by 未来人来xw on 2019/8/2.
 */
public class ForwardBeforeLoginInteceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路径的uri
        String uri = request.getRequestURI();

        // 想要跳转到登录页
        // 1 已经登录的状态不能进入该页面
        // 2 未登录转台可以进入
        if(uri.endsWith("/login/toLogin"))
        {
            //当前处于已经登录的状态了 直接跳转到首页
            if(request.getSession() != null &&
                    request.getSession().getAttribute("username") != null){
                System.out.println("地方1 ："+request.getContextPath());
                response.sendRedirect(request.getContextPath() + "/requirementManage/gotoRecordList");
                return false;
            }else{
                System.out.println("地方4 ："+request.getContextPath());
                return true;
            }
        }

        // 其他情况判断session中是否有key，有的话继续用户的操作
        if(request.getSession() == null ||
                request.getSession().getAttribute("username") == null){
            System.out.println("地方2 ："+request.getContextPath());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return false;
        }
        // 最后的情况就是进入登录页面
        System.out.println("地方3 ："+request.getContextPath());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
