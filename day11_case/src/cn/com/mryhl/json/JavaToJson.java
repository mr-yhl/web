package cn.com.mryhl.json;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.util.DataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaToJson {
    // 将user对象转为json

    @Test
    public void test01() throws Exception{
        List<User> list = DataUtils.readAll();
        User user = list.get(0);
        // 输出获取到的内容
        System.out.println(user);


        // 创建核心转换对象
        ObjectMapper om = new ObjectMapper();
        // 将user对象转换成json字符串
        String json = om.writeValueAsString(user);
        System.out.println(json);

    }

    // 将list集合（array）转换json
    @Test
    public void test02() throws Exception{
        List<User> list = DataUtils.readAll();

        // 输出获取到的内容
        System.out.println(list);


        // 创建核心转换对象
        ObjectMapper om = new ObjectMapper();
        // 将user对象转换成json字符串
        String json = om.writeValueAsString(list);
        System.out.println(json);

    }



    // 将map集合转为json
    @Test
    public void test03() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("id", "001");
        map.put("username", "詹姆斯·邦德");
        map.put("age", 77);

        System.out.println(map);


        // 创建核心转换对象
        ObjectMapper om = new ObjectMapper();
        // 将user对象转换成json字符串
        String json = om.writeValueAsString(map);
        System.out.println(json);

    }


}
