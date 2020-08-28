package cn.com.mryhl.b_case;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 初始化在线人数
 * @author mryhl
 */
@WebListener
public class InintCountPersonListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 获取servletContext
        ServletContext servletContext = servletContextEvent.getServletContext();
        // 初始化人数0
        servletContext.setAttribute("countPerson",0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
