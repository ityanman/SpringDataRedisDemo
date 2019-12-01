import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class TestHash {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void setHash(){
        redisTemplate.boundHashOps("myHash").put("iphone","1299");
        redisTemplate.boundHashOps("myHash").put("HUAWEI","8799");
        redisTemplate.boundHashOps("myHash").put("HTC","5499");
    }
    @Test
    public void getHash(){
        String name = (String) redisTemplate.boundHashOps("myHash").get("iphone");
        System.out.println(name);
    }
    //获取所有
    @Test
    public void getAll(){
        Map myHash = redisTemplate.boundHashOps("myHash").entries();
        System.out.println(myHash);

        Set<Map.Entry<String,String>> set = myHash.entrySet();
        for (Map.Entry<String, String> entry : set) {
            System.out.println(entry);
            String value = entry.getValue();
            System.out.println(value);
        }
    }
}
