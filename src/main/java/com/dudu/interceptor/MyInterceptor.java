package com.dudu.interceptor;

import com.dudu.tools.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {

    /**
     * 到达控制器之前
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        boolean checkValidToken = validateToken(token);
        if (!checkValidToken) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    private boolean validateToken(String token) {
        try {
            String jwtToken = token.substring(7);
            Claims claims = JwtUtil.parseToken(jwtToken);

            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 控制器处理完请求后
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // HandlerMethod handlerMethod = (HandlerMethod) handler;
        // Method method = handlerMethod.getMethod();
        // long startTime = (Long) request.getAttribute("requestStartTime");
        // long endTime = System.currentTimeMillis();
        // long executeTime = endTime - startTime;
        // // 打印方法执行时间
        // if (executeTime > 1000) {
        //     System.out.println("[" + method.getDeclaringClass().getName() + "." + method.getName() + "] 执行耗时 : "
        //             + executeTime + "ms");
        // } else {
        //     System.out.println("[" + method.getDeclaringClass().getSimpleName() + "." + method.getName() + "] 执行耗时 : "
        //             + executeTime + "ms");
        // }
    }


    /**
     * 请求完成后
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
