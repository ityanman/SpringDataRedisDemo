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
public class TestObject {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void setObject(){
        Person person1 = new Person();
        person1.setName("zs");
        person1.setAge(22L);

        Person person2 = new Person();
        person2.setName("ls");
        person2.setAge(29L);

        ArrayList<Person> personList = new ArrayList();
        personList.add(person1);
        personList.add(person2);
        /*分业务*/
        redisTemplate.boundHashOps("userService").put("person",personList);
    }
    @Test
    public void getObject(){
        List<Person> personList = ( List<Person>)redisTemplate.boundHashOps("userService").get("person");
        for (Person person : personList) {
            String name = person.getName();
            System.out.println(name);
        }
    }
}
