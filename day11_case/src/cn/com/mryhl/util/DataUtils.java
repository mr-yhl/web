package cn.com.mryhl.util;


import cn.com.mryhl.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    private static String realpath = "d:/userdata.txt";

    //从文件中读取所有学员信息
    public static List<User> readAll() {
        //保存所有学生对象信息
       List<User> list = new ArrayList<>();
        try {
            //得到文件真实路径
            //创建字符输入流
            Reader isr = new InputStreamReader(new FileInputStream(realpath), "UTF-8");
            //创建字符缓冲流
            BufferedReader br = new BufferedReader(isr); //装饰模式

            //一次读一行
            String row = null;
            while ((row = br.readLine()) != null) {//row = "1,张三,男,20"
                String[] arr = row.split(",");
                User user = new User();
                user.setId(arr[0]);
                user.setName(arr[1]);
                user.setSex(arr[2]);
                user.setAge(Integer.parseInt(arr[3]));
                user.setAddress(arr[4]);
                user.setQq(arr[5]);
                user.setEmail(arr[6]);
                //将User对象添加到集合
                list.add(user);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //向文件中写入所有用户信息--覆盖写
    public static void writeAll(List<User> list) {
        try {
            //创建字符输出流
            Writer osw = new OutputStreamWriter(new FileOutputStream(realpath), "UTF-8");
            //创建字符缓冲流
            BufferedWriter out = new BufferedWriter(osw);
            //循环向文件中写入文本
            for (User user : list) {
                out.write(user.getId() + "," + user.getName() + "," + user.getSex() + "," + user.getAge() + "," + user.getAddress() + "," + user.getQq() + "," + user.getEmail());
                out.newLine();//创建新的一行
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<User> list = readAll();

    }
}