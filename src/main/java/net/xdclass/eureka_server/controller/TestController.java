//package net.xdclass.eureka_server.controller;
//
//import com.google.common.hash.BloomFilter;
//import lombok.extern.slf4j.Slf4j;
//import net.xdclass.eureka_server.domain.entity.Student;
//import net.xdclass.eureka_server.domain.entity.User;
//import net.xdclass.eureka_server.logic.RedisClient;
//import net.xdclass.eureka_server.service.BloomFilterService;
//import net.xdclass.eureka_server.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @ClassName TestController
// * @Description TODO
// * @Author Administrator
// * @dATE 2019/6/27 22:37
// * @Version 1.0
// **/
//@RestController
//@Slf4j
//public class TestController {
//
//   @Autowired
//    private RedisClient redisClient;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private BloomFilterService bloomFilterService;
//
//    @GetMapping(value="getCache")
//    public Student getStudent(){
//        return redisClient.getStudentById("123");
//    }
//    /**
//     *方法被调用且更新缓存
//     * @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
//     *    condition : 如果条件满足则使用缓存，否则不使用缓存
//     *    result.userId 的意思是User对象中的getUserId() 方法返回的结果
//     * @return
//     */
//    @GetMapping ("/api/insert")
//    public User saveUser(@RequestParam("name")String name,
//                         @RequestParam("age")Integer age,
//                         @RequestParam("role")Long role,
//                         @RequestParam("phone")String phone){
//        log.info("正在开始保存user值...............");
//        User user = new User();
//        user.setAge(age);
//        user.setName(name);
//        user.setPhone(phone);
//        user.setRole(role);
//        user.setName(name);
//        redisClient.saveUser(user);
//        return user;
//    }
//
//    @GetMapping("/api/save")
//    public String saveRedis() {
//        redisTemplate.opsForValue().set("test","test1");
//        return (String)redisTemplate.opsForValue().get("test");
//    }
//
//    @GetMapping("/api/query")
//    public User queryUserById(@RequestParam("id")String id) {
//        return redisClient.queryUser(id);
//    }
//
//    @GetMapping("api/delete")
//    public void delete(@RequestParam("id")Integer id) {
//        redisClient.delete(id);
//    }
//
//    @GetMapping("api/bloomfilter")
//    public boolean bloomfilter(@RequestParam("id")Integer id) {
//        return bloomFilterService.keyExists(id);
//    }
//}
