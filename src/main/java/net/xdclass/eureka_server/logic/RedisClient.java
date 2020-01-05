package net.xdclass.eureka_server.logic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.eureka_server.domain.entity.Student;
import net.xdclass.eureka_server.domain.entity.User;
import net.xdclass.eureka_server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisClient
 * @Description TODO
 * @Author Administrator
 * @dATE 2019/6/27 21:55
 * @Version 1.0
 **/
@Component
@Slf4j
public class RedisClient {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IUserService userService;

    public void set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, Integer.parseInt(value), 10000, TimeUnit.SECONDS);
        }catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }
    }

    public String get(final String key){
        try {
            return String.valueOf(redisTemplate.opsForValue().get(key));
        }catch (Exception e){
            log.error("Exception: ;" + e.getMessage());
        }
        return null;
    }

    public Long decr(String key,int value) {
        try{
            return redisTemplate.opsForValue().increment(key,-value);
        }catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }
        return 0L;
    }

    @Cacheable(cacheNames="student",key = "#id")
    public Student getStudentById(String id) {
        System.out.println("运行service方法，获取学生信息id" + id);
        Student student = new Student(Integer.parseInt(id), "name+" + Integer.parseInt(id), 18);
        return student;
    }

   // @CachePut(cacheNames="user",key = "T(java.util.UUID).randomUUID().toString()")
    @CachePut(cacheNames="user")
    public User saveUser(User user) {
        //log.info("插入数据库数据: " + user.toString());
       boolean success =  userService.insert(user);
       if (success) {
          Integer id = user.getId();
           user.setId(id);
       }

        return user;
    }

    /**
     * @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     *      unless 表示条件表达式成立的话不放入缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames="user",key="#id")
    public User queryUser(String id) {
        log.info("从数据库中查询数据: " + id);
        User user = userService.selectOne(new EntityWrapper<User>().eq("id",id));
        return user;
    }

    @CacheEvict(cacheNames = "user",key="#id")
    public void delete(@RequestBody Integer id){
        log.info("删除数据库中指定的key: " + id);
    }
}
