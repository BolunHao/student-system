package com.management.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.mapper.Mapper.UserRoleMapper;
import com.management.Parametric.UserRole;
import com.management.service.services.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
