package net.xdclass.eureka_server.domain.mapper;

import net.xdclass.eureka_server.domain.entity.RedPacketInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 红包信息
表，新建⼀个红包插⼊⼀条记录 Mapper 接口
 * </p>
 *
 * @author 01368991
 * @since 2019-11-09
 */
public interface RedPacketInfoMapper extends BaseMapper<RedPacketInfo> {

    @Update("update red_packet_info set remaining_amount = remaining_amount - #{amount},remaining_packet = remaining_packet-1 where red_packet_id = #{redPacketId}")
    Integer updateRedPacketInfoById( @Param("amount")Integer amount,@Param("redPacketId")Long redPacketId);
}
