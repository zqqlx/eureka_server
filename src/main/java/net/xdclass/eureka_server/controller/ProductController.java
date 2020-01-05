package net.xdclass.eureka_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import net.xdclass.eureka_server.logic.ApiHttpClient;
import net.xdclass.eureka_server.logic.RedisClient;
import net.xdclass.eureka_server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;

@RestController
@RequestMapping(value="/api/test")
public class ProductController {
/*  @Autowired private ProductService productService;

    @Autowired private RedisClient redisClient;

    @RequestMapping("product")
    public Object listProduct(){

       return redisClient.setObject("key","value");
    }*/

@Autowired
    private ApiHttpClient apiHttpClient;

    @GetMapping("/")
    public String test() {
        String url = "http://localhost:8888/api/test/json";
        JSONObject message = new JSONObject();
        message.put("abc","abc");
        apiHttpClient.doPostJson(url,message.toJSONString(),null);
    return "Ok";
    }

    @PostMapping("/json")
    public String json(@RequestBody JSONObject m){
        return m.toJSONString();
    }

}
