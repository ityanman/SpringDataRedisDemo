import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class TestString {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void setString(){
        redisTemplate.boundValueOps("name3").set("zs");
    }

    @Test
    public void testString(){
        //redisTemplate.boundValueOps("name").set("myxq");
        String name = (String)redisTemplate.boundValueOps("name").get();
        System.out.println(name);
    }

    @Test
    public void testList(){
       /* redisTemplate.boundListOps("myListKey").leftPush("001");
        redisTemplate.boundListOps("myListKey").leftPush("002");
        redisTemplate.boundListOps("myListKey").leftPush("003");
        redisTemplate.boundListOps("myListKey").leftPush("004");*/

        List myListKey = redisTemplate.boundListOps("myListKey").range(0, -1);
        for (Object o : myListKey) {
            System.out.println(o);
        }
    }

    @Test
    public void testHash(){
        /**
         key       keyMap   keyValue
         myMapKey  name     zs
         myMapKey  age      10
         */
     /*   redisTemplate.boundHashOps("myMapKey").put("name","zs");
        redisTemplate.boundHashOps("myMapKey").put("age","10");
        redisTemplate.boundHashOps("myMapKey").put("phone","10010");*/

        Map map = redisTemplate.boundHashOps("myMapKey").entries();
        Set<Map.Entry<String,String>> set = map.entrySet();
        for (Map.Entry<String, String> entry : set) {
            /*System.out.println(entry);*/
            Object myMapKey = redisTemplate.boundHashOps("myMapKey").get(entry.getKey());
            System.out.println(myMapKey);
        }

    }




}
