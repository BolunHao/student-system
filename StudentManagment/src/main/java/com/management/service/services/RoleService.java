package com.management.service.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     * @desc: query role
     */
    List<Role> findRoleByPage(Page<Role> page, String roleName);

    /**
     * @desc: Add role
     */
    Object addRoles(Role vo);

    /**
     * @desc: remove role
     */
    Object delRole(String[] ids);

    /**
     * The Bind role drop-down box
     */
    List<Role> findAllRoles();
}
