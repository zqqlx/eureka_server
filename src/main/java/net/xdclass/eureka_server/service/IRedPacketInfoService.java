package net.xdclass.eureka_server.service;

import net.xdclass.eureka_server.domain.entity.RedPacketInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 红包信息
表，新建⼀个红包插⼊⼀条记录 服务类
 * </p>
 *
 * @author 01368991
 * @since 2019-11-09
 */
public interface IRedPacketInfoService extends IService<RedPacketInfo> {

    Integer saveRedPacketInfoService(RedPacketInfo redPacketInfo);

    Integer updateRedPacketInfcByUid(Long redPacketId,Integer amount);

}
