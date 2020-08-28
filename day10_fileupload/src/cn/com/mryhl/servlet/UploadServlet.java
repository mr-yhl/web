package cn.com.mryhl.servlet;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        // 1. 创建文件解析器（磁盘文件项）工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 2. 创建文件解析器对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        try {
            // 3. 通过解析器对象，解析request请求（请求头和请求体），返回上传项集合
            List<FileItem> fileItemList = servletFileUpload.parseRequest(request);

            //  System.out.println(fileItemList.size());
            // 4. 遍历上传项集合

            for (FileItem fileItem : fileItemList) {
                // 普通文本项
                if (fileItem.isFormField()) {
                    // nickname
                    String name = fileItem.getFieldName();
                    // lucy
                    String value = fileItem.getString();
                    System.out.println(name + "=" + value);
                } else { // 上传文件项
                    // 获取原文件名
                    String filename = fileItem.getName();

                    // 新文件名
                    String uuid = IdUtil.simpleUUID();

                    // 获取扩展名
                    String ext = FileUtil.extName(filename);

                    // 组成完整文件名
                    filename = uuid + "." + ext;
                    // 获取文件的io流
                    InputStream in = fileItem.getInputStream();
                    System.out.println("文件名：" + filename);
                    // System.out.println(in);
                    // 指定文件的保存路径
                    FileOutputStream out = new FileOutputStream(new File("d:/" + filename));

                    // 拷贝
                    IoUtil.copy(in, out);

                    // 释放资源
                    out.close();
                    in.close();
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        response.getWriter().write("上传成功...");
    }
}