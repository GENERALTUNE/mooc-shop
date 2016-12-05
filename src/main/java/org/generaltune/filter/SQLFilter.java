package org.generaltune.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by zhumin on 2016/12/5.
 */
////过滤sql关键字的Filter
public class SQLFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        //获得所有请求参数名
        Enumeration params = req.getParameterNames();

        String sql = "";
        while (params.hasMoreElements())
        {
            // 得到参数名
            String name = params.nextElement().toString();
            // System.out.println("name===========================" + name +
            // "--");
            // 得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++)
            {
                sql = sql + value[i];
            }
        }
        System.out.println("提交方式:"+req.getMethod());
        System.out.println("被匹配字符串：" + sql);
        if (sqlValidate(sql))
        {
//            第一种//请求转发
//
//            req.getRequestDispatcher("error.jsp").
//                    forward(req, res);

//      //第二种      传送json数据
//            JSONObject json = new JSONObject();
//            json.accumulate("message", "恶意代码注入");
//            res.getWriter().print(json.toString());

            //第三种
            req.getSession().setAttribute("error_message","恶意注入了");
            throw new  RuntimeException("恶意注入");


        }
        else
        {
            String   request_uri   =   req.getRequestURI();
            filterChain.doFilter(servletRequest, servletResponse);
        }



    }

    // 校验
    protected static boolean sqlValidate(String str)
    {
        str = str.toLowerCase();// 统一转为小写
        // String badStr = "and|exec";
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|chr|mid|master|truncate|char|declare|sitename|net user|xp_cmdshell|or|like|;|--|+|,|*|/";
        /*
         * String badStr =
         * "'|and|exec|execute|insert|create|drop|table|from|grant|use|group_concat|column_name|"
         * +
         * "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|"
         * + "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";
         */// 过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++)
        {
            if (str.indexOf(badStrs[i]) != -1)
            {
                System.out.println("匹配到：" + badStrs[i]);
                return true;
            }
        }
        return false;
    }


    public void destroy() {
        // throw new UnsupportedOperationException("Not supported yet.");
    }
}
