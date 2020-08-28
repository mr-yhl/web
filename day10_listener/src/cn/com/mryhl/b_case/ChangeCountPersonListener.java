package cn.com.mryhl.b_case;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 11063
 */
@WebListener
public class ChangeCountPersonListener implements HttpSessionListener {

    /**
     * 会话建立,人数+1
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // 获取最大的域对象
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        // 取出原来的人数
        Integer oldCountPerson = (Integer) servletContext.getAttribute("countPerson");
        // 加一覆盖
        servletContext.setAttribute("countPerson", oldCountPerson + 1);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        // 获取最大的域对象
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        // 取出原来的人数
        Integer oldCountPerson = (Integer) servletContext.getAttribute("countPerson");
        // 减一覆盖
        servletContext.setAttribute("countPerson", oldCountPerson - 1);
    }
}
