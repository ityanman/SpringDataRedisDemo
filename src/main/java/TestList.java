import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class TestList {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void setList(){
        redisTemplate.boundListOps("myList").leftPush("zs","ls");
        redisTemplate.boundListOps("myList").leftPush("ee");
        redisTemplate.boundListOps("myList").leftPush("ww");
    }
    @Test
    public void getList(){
        List<String> myList = redisTemplate.boundListOps("myList").range(0, -1);
        for (Object o : myList) {
            System.out.println(o);
        }
    }
    //删除
    @Test
    public void delList(){
        redisTemplate.delete("myList");
    }
}
