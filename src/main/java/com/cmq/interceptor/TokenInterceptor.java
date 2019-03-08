package com.cmq.interceptor;

import com.alibaba.fastjson.JSON;
import com.cmq.common.BaseResult;
import com.cmq.common.CmqSystem;
import com.cmq.common.SystemUser;
import com.cmq.utils.JwtUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义token拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletResponse.setCharacterEncoding("utf-8");

        //get token from request header
        String token = httpServletRequest.getHeader("token");

        if(!StringUtils.isEmpty(token)){
            boolean verify = JwtUtils.verify(token);
            if(verify){
                SystemUser user = new SystemUser(JwtUtils.parseInteger(token, "id"), JwtUtils.parseString(token, "mobile"));
                CmqSystem.setCurrentLoggedInUser(user);
                return true;
            }
        }

        BaseResult fail = new BaseResult().fail("认证失败");
        httpServletResponse.getWriter().write(JSON.toJSONString(fail));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        CmqSystem.setCurrentLoggedInUser(null);
    }
}
