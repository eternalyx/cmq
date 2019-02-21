package com.tcm.questionnaire.interceptor;

import com.alibaba.fastjson.JSON;
import com.tcm.questionnaire.common.BaseResult;
import com.tcm.questionnaire.common.SystemThreadLocal;
import com.tcm.questionnaire.utils.JwtUtil;
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
            boolean verify = JwtUtil.verify(token);
            if(verify){
                Integer userId = JwtUtil.parse(token, "userId");
                SystemThreadLocal.setSystemUserId(userId);
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
        SystemThreadLocal.setSystemUser(null);
    }
}
