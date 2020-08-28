package cn.com.mryhl.e_case;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * 敏感字替换
 */

@WebFilter("/WordServlet")
public class WordProFilter implements Filter {
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
        // 对request对象进行装饰
        System.out.println("替换的过滤器");

        HttpServletRequest requestDecorator = new MyRequest(request, wordArray);
        // 放行
        filterChain.doFilter(requestDecorator, response);
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
