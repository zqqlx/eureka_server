package net.xdclass.eureka_server.service.impl;

import net.xdclass.eureka_server.domain.entity.RedPacketInfo;
import net.xdclass.eureka_server.domain.mapper.RedPacketInfoMapper;
import net.xdclass.eureka_server.service.IRedPacketInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 红包信息
表，新建⼀个红包插⼊⼀条记录 服务实现类
 * </p>
 *
 * @author 01368991
 * @since 2019-11-09
 */
@Service
public class RedPacketInfoServiceImpl extends ServiceImpl<RedPacketInfoMapper, RedPacketInfo> implements IRedPacketInfoService {

    @Autowired
    private RedPacketInfoMapper redPacketInfoMapper;

    @Override
    public Integer saveRedPacketInfoService(RedPacketInfo redPacketInfo) {
        return redPacketInfoMapper.insert(redPacketInfo);
    }

    @Override
    public Integer updateRedPacketInfcByUid(Long redPacketId,Integer amount) {
        return redPacketInfoMapper.updateRedPacketInfoById(amount,redPacketId);
    }
}
