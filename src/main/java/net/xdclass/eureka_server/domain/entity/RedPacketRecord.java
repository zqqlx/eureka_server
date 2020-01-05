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
 * 抢红包记
录表，抢⼀个红包插⼊⼀条记录
 * </p>
 *
 * @author 01368991
 * @since 2019-11-09
 */
@Data
@Accessors(chain = true)
@TableName("red_packet_record")
public class RedPacketRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 抢到红包的⾦额
     */
    private Integer amount;
    /**
     * 抢到红包的⽤户的⽤户
名
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 抢到红包的⽤户的头像
     */
    @TableField("img_url")
    private String imgUrl;
    /**
     * 抢到红包⽤户的⽤户标识
     */
    private Integer uid;
    /**
     * 红包id，采⽤
timestamp+5位随机数
     */
    @TableField("red_packet_id")
    private Long redPacketId;
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
