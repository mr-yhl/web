package cn.com.mryhl.a_listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    /*
        监听：ServletContext创建
            servletContextEvent：监听器事件对象..可以获取servletContext域对象
     */

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener创建了....");
        ServletContext servletContext = servletContextEvent.getServletContext();
    }

    /*
        监听：ServletContext销毁
     */

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener销毁了...");
    }
}
