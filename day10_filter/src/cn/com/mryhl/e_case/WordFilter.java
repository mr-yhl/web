package cn.com.mryhl.e_case;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * 敏感字符拦截
 */

// @WebFilter("/WordServlet")
public class WordFilter implements Filter {
    private String[] wordArray;
    @Override
    public void destroy() {
    }

    /**
     * 拦截非法词汇
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1. 获取请求参数
        String word = request.getParameter("word");

        // 2.校验词库
        for (String illegqlity : wordArray) {
            if (word.contains(illegqlity)){
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("言论非法...");
                return;
            }
        }

        // 放行
        filterChain.doFilter(request, response);
    }
    /**
     * 加载非法词库
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        // 1.专门加载src目录下的properties配置文件，sun公司提供了一个工具类（ResourceBundle）
        ResourceBundle word = ResourceBundle.getBundle("word");
        // 2.获取关键字信息
        String keyword = word.getString("keyword");

        // 3.转为数组
        wordArray = keyword.split(",");


        System.out.println("非法词库加载成功：" + Arrays.toString(wordArray));
    }

}
