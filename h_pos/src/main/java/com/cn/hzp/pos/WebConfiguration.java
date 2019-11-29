package com.cn.hzp.pos;


import com.cn.hzp.pos.bean.User;
import com.cn.hzp.pos.dao.impl.UserDao;
import org.apache.catalina.filters.RemoteIpFilter;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
public class WebConfiguration {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }



    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        System.out.println("==============testFilterRegistration");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    public class MyFilter implements Filter {
        @Autowired
        private UserDao userDao;
        @Override
        public void destroy() {
            // TODO Auto-generated method stub
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            System.out.println("==============doFilter");

            // TODO Auto-generated method stub
            HttpServletRequest request = (HttpServletRequest) srequest;
            HttpServletResponse response = (HttpServletResponse) sresponse;
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            String userid = request.getParameter("userid");
            System.out.println("this is MyFilter,url :"+request.getRequestURI()+";Method="+request.getMethod()+"params="+userid);
            String str = request.getRequestURI();
            if (str.equals("/selectUser")){
                User user = userDao.queryUserByPhone(userid);
                System.out.println("this is MyFilter,url =:"+request.getRequestURI()+";respone="+response.getStatus());
                JSONObject obj = new JSONObject();
                try {
                    obj.put("code",200);
                    obj.put("msg","未登录");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(obj.toString());

            }else {
                //response.sendRedirect("login");
                filterChain.doFilter(srequest, sresponse);
            }
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
            // TODO Auto-generated method stub
            System.out.println("==============init");
        }

        public  String toConvertString(InputStream is) {
            StringBuffer res = new StringBuffer();
            // 把字节流转化为字符流
            InputStreamReader isr = new InputStreamReader(is);
            // 普通的Reader读取输入内容时依然不太方便，可以将普通的
            // Reader再次包装成BufferedReader,利用BufferReader的readLine()
            // 方法可以一次读取一行内容
            BufferedReader read = new BufferedReader(isr);
            try {
                String line;
                line = read.readLine();
                while (line != null) {
                    res.append(line + "<br>");
                    line = read.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != isr) {
                        isr.close();
                        isr.close();
                    }
                    if (null != read) {
                        read.close();
                        read = null;
                    }
                    if (null != is) {
                        is.close();
                        is = null;
                    }
                } catch (IOException e) {
                }
            }
            return res.toString();
        }
    }
}
