package com.management.service.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     * @desc: Query Role
     */
    List<Role> findRoleByPage(Page<Role> page, String roleName);

    /**
     * @desc: New Characters
     */
    Object addRoles(Role vo);

    /**
     * @desc: Delete Role
     */
    Object delRole(String[] ids);

    /**
     * Bind Role drop-down box
     */
    List<Role> findAllRoles();
}
