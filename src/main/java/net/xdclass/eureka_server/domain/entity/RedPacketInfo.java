package net.xdclass.eureka_server.domain.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 红包信息
表，新建⼀个红包插⼊⼀条记录
 * </p>
 *
 * @author 01368991
 * @since 2019-11-09
 */
@Data
@Accessors(chain = true)
@TableName("red_packet_info")
public class RedPacketInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 红包id，采⽤
timestamp+5位随机数
     */
    @TableField("red_packet_id")
    private Long redPacketId;
    /**
     * 红包总⾦额，单位分
     */
    @TableField("total_amount")
    private Integer totalAmount;
    /**
     * 红包总个数
     */
    @TableField("total_packet")
    private Integer totalPacket;
    /**
     * 剩余红包⾦额，单位
分
     */
    @TableField("remaining_amount")
    private Integer remainingAmount;
    /**
     * 剩余红包个数
     */
    @TableField("remaining_packet")
    private Integer remainingPacket;
    /**
     * 新建红包⽤户的⽤户标识
     */
    private Integer uid;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
