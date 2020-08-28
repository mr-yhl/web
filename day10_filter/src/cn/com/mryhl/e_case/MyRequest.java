package cn.com.mryhl.e_case;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


/*
    对 getParameter方法进行增强
 */

public class MyRequest extends HttpServletRequestWrapper {

    // 获取非法词库
    private String[] wordArray;

    public MyRequest(HttpServletRequest request, String[] wordArray) {
        super(request);
        this.wordArray = wordArray;

    }

    // 重写getProameter方法

    @Override
    public String getParameter(String name) {
        // 获取用户输入的内容
        String parameter = super.getParameter(name);
        // 遍历和判断的
        for (String s : wordArray) {
            // 替换
            if (parameter.contains(s)) {
                parameter = parameter.replaceAll(s,"***");
            }
        }

        return parameter;
    }
}
