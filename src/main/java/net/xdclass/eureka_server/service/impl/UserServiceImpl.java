package net.xdclass.eureka_server.service.impl;

import net.xdclass.eureka_server.domain.entity.User;
import net.xdclass.eureka_server.domain.mapper.UserMapper;
import net.xdclass.eureka_server.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 01368991
 * @since 2019-06-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
