package com.wjc.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjc.server.entity.User;
import com.wjc.server.mapper.UserMapper;
import com.wjc.server.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author wjc
 * @description
 * @date 2020/12/1
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
