package cn.com.mryhl.d_download;

import cn.hutool.core.io.IoUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收请求参数名
        String filename = request.getParameter("filename");
        // 2.获取该文件真实路径，并封装到字节输入流
        ServletContext servletContext = getServletContext();
        String filePath = servletContext.getRealPath("/download/" + filename);
        FileInputStream in = new FileInputStream(filePath);

        // 3.准备response的字节输出流
        ServletOutputStream out = response.getOutputStream();

        // 4.告知浏览器下载文件的MIME类型
        String mimeType = servletContext.getMimeType(filename);
        response.setContentType(mimeType);

        // 解决文件名乱码问题
        filename = DownLoadUtils.getName(request.getHeader("user-agent"), filename);
        // 5.告知浏览器以附件形式保存文件
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        // 6.io的拷贝
       /*byte[] b = new byte[4096];
        int len;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }*/

        IoUtil.copy(in, out);

        // 7.释放资源
        out.close();
        in.close();
    }

}