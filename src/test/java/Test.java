import cn.inkroom.web.quartz.config.Constants;
import cn.inkroom.web.quartz.redis.JedisClientPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
        JedisClientPool pool = context.getBean(JedisClientPool.class);
        try {
            System.out.println(pool.get(Constants.KEY_REDIS_UPLOAD_BASE_PATH));

            pool.set(Constants.KEY_REDIS_UPLOAD_BASE_PATH,"/home/project/upload/image/");


            System.out.println(pool.get(Constants.KEY_REDIS_UPLOAD_BASE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
