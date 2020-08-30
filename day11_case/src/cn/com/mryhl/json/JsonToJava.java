package cn.com.mryhl.json;

import cn.com.mryhl.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class JsonToJava {
    /**
     * json字符串转为 user对象
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        // 准备数据
        String json = "{\"id\":\"1\",\"name\":\"jack\",\"sex\":\"男\",\"age\":28,\"address\":\"香港\",\"qq\":\"123456\",\"email\":\"123456@qq.com\"}";
        // 创建核心转换器
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(json, User.class);
        System.out.println(user);

    }


    /**
     *  json字符串转为 list集合
     */
    @Test
    public void test02() throws Exception{
        // 准备数据
        String json = "[{\"id\":\"1\",\"name\":\"jack\",\"sex\":\"男\",\"age\":28,\"address\":\"香港\",\"qq\":\"123456\",\"email\":\"123456@qq.com\"},{\"id\":\"2\",\"name\":\"lucy\",\"sex\":\"男\",\"age\":38,\"address\":\"香港\",\"qq\":\"223456\",\"email\":\"223456@qq.com\"},{\"id\":\"4\",\"name\":\"lufei\",\"sex\":\"男\",\"age\":48,\"address\":\"北京\",\"qq\":\"1233212\",\"email\":\"guodegang@163.com\"},{\"id\":\"5\",\"name\":\"杨妞妞\",\"sex\":\"男\",\"age\":19,\"address\":\"葫芦岛\",\"qq\":\"996655\",\"email\":\"996655@qq.com\"},{\"id\":\"6\",\"name\":\"小龙女\",\"sex\":\"女\",\"age\":18,\"address\":\"古墓派\",\"qq\":\"996633\",\"email\":\"996633@qq.com\"}]";
        // 创建核心转换器
        ObjectMapper om = new ObjectMapper();
        // 转换为list集合
        Object list = om.readValue(json, List.class);
        System.out.println(list);
    }

    /**
     * json字符串转为 map集合
     */
    @Test
    public void test03() throws Exception{
        // 准备数据
        String json = "{\"id\":\"001\",\"age\":77,\"username\":\"詹姆斯·邦德\"}";

        // 创建核心转换器对象
        ObjectMapper om = new ObjectMapper();
        Map map = om.readValue(json, Map.class);
        System.out.println(map);
    }
}
