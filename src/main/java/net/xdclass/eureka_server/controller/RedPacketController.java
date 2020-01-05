package net.xdclass.eureka_server.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.eureka_server.domain.entity.RedPacketInfo;
import net.xdclass.eureka_server.domain.entity.RedPacketRecord;
import net.xdclass.eureka_server.domain.mapper.RedPacketRecordMapper;
import net.xdclass.eureka_server.exception.BusinessException;
import net.xdclass.eureka_server.exception.BusinessExceptionConstnts;
import net.xdclass.eureka_server.logic.RedisClient;
import net.xdclass.eureka_server.service.IRedPacketInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1")
public class RedPacketController {

    @Autowired
    private IRedPacketInfoService redPacketInfoService;

    @Autowired
    private RedPacketRecordMapper redPacketRecordMapper;

    @Autowired
    private RedisClient redisClient;

    /**
     * 发红包：
     * 新增⼀条红包记录
     * 往mysql⾥⾯添加⼀条红包记录
     * 往redis⾥⾯添加⼀条红包数量记录 decr
     * 往redis⾥⾯添加⼀条红包⾦额记录 decreby
     * @param uid
     * @param totalAmout
     * @param totalNum
     * @return
     */
    @GetMapping("/saveRedPacket")
    public String saveRedPacketInfo(@RequestParam("uid") Integer uid, @RequestParam("totalAmount") Integer totalAmout,@RequestParam("totalNum")Integer totalNum){
        RedPacketInfo redPacketInfo = new RedPacketInfo();
        redPacketInfo.setUid(uid);
        redPacketInfo.setTotalAmount(totalAmout);
        redPacketInfo.setTotalPacket(totalNum);
        redPacketInfo.setRemainingAmount(totalAmout);
        redPacketInfo.setRemainingPacket(totalNum);
        Random random = new Random();
        long redPacketId = System.currentTimeMillis() + random.nextInt(10000);
        redPacketInfo.setRedPacketId(redPacketId);
        redPacketInfo.setCreateTime(new Date());
        Integer ret = redPacketInfoService.saveRedPacketInfoService(redPacketInfo);
        if (ret != null && ret.intValue() > 0) {
            redisClient.set(String.valueOf(redPacketId)+"_totalAmout",String.valueOf(totalAmout));
            redisClient.set(String.valueOf(redPacketId) + "_totalNum",String.valueOf(totalNum));

        } else {
            throw new BusinessException(BusinessExceptionConstnts.CODE_500,"保存红包信息异常！");
        }
        return JSONObject.toJSONString(redPacketInfo);
    }

    /**
     * 抢红包：
     * 抢红包功能属于原⼦减操作
     * 当⼤⼩⼩于0时原⼦减失败
     * 将红包ID的请求放⼊请求队列中，如果发现超过红包的个数，直接返回
     * 类推出token令牌和秒杀设计原理
     * @param uid
     * @param redPacketId
     * @return
     */
    @GetMapping("/getRedPacket")
    public String getRedPacket(@RequestParam("uid") Integer uid, @RequestParam("redPacketId")long redPacketId) {
       String key = String.valueOf(redPacketId) + "_totalNum";
       String totalNum = redisClient.get(key);
        if (StringUtils.isBlank(totalNum) || !totalNum.matches("[1-9][0-9]*")||Integer.parseInt(totalNum) <=0) {
            throw new BusinessException(BusinessExceptionConstnts.CODE_500,"红包已经抢购完了!");
        }
        redisClient.decr(key,1);
        return "OK";
    }

    /**
     * 拆红包：
     * 拆红包才是⽤户能领导红包
     * 这时候要先减redis⾥⾯的⾦额和红包数量
     * 减完⾦额再⼊库
     * @param uid
     * @param redPacketId
     * @return
     */
    @GetMapping("/getRedPacketMoney")
    public String getRedPacketMoney(@RequestParam("uid") Integer uid, @RequestParam("redPacketId")long redPacketId) {
        String strRedPacketId = String.valueOf(redPacketId);
        String key = strRedPacketId + "_totalNum";
        String totalNum = redisClient.get(key);
        if (StringUtils.isBlank(totalNum) || !totalNum.matches("[1-9][0-9]*")||Integer.parseInt(totalNum) <=0) {
            throw new BusinessException(BusinessExceptionConstnts.CODE_500,"红包已经抢购完了!");
        }
        String totalAmountKey = strRedPacketId + "_totalAmout";
        String totalAmount = redisClient.get(totalAmountKey);
        if (StringUtils.isBlank(totalAmount) || Integer.parseInt(totalAmount) <= 0) {
            throw new BusinessException(BusinessExceptionConstnts.CODE_500,"红包已经抢购完了!");
        }
        Integer totalAmountInt = Integer.parseInt(totalAmount);
        Integer totalNumInt = Integer.parseInt(totalNum);
        Integer maxAmount =  totalAmountInt/(totalNumInt*2);
        Random maxRandom = new Random();
        Integer maxRandomInt = maxRandom.nextInt(maxAmount);
        redPacketInfoService.updateRedPacketInfcByUid(redPacketId,maxAmount);
        RedPacketRecord redPacketRecord = new RedPacketRecord();
        redPacketRecord.setUid(uid);
        redPacketRecord.setRedPacketId(redPacketId);
        redPacketRecord.setAmount(maxAmount);
        redPacketRecord.setCreateTime(new Date());
        redPacketRecord.setNickName("zqq");
        redPacketRecord.setImgUrl("url");
        redPacketRecordMapper.insert(redPacketRecord);
        return String.valueOf(maxRandomInt);
    }

}
