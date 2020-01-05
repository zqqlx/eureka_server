package net.xdclass.eureka_server.service.impl;

import net.xdclass.eureka_server.domain.entity.RedPacketRecord;
import net.xdclass.eureka_server.domain.mapper.RedPacketRecordMapper;
import net.xdclass.eureka_server.service.IRedPacketRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 抢红包记
录表，抢⼀个红包插⼊⼀条记录 服务实现类
 * </p>
 *
 * @author 01368991
 * @since 2019-11-09
 */
@Service
public class RedPacketRecordServiceImpl extends ServiceImpl<RedPacketRecordMapper, RedPacketRecord> implements IRedPacketRecordService {

}
