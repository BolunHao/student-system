package com.management.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.mapper.Mapper.RoleMapper;
import com.management.Parametric.Role;
import com.management.service.services.RoleService;
import com.management.utils.EnumCode;
import com.management.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * @desc: qury role
     */
    public List<Role> findRoleByPage(Page<Role> page,String roleName) {
        return super.baseMapper.findRoleByPage(page,roleName);
    }

    /**
     * @desc: add role
     */
    @Transactional
    public Object addRoles(Role vo) {
        Role r = new Role();
        r.setName(vo.getName());
        r.setRoleDesc(vo.getRoleDesc());
        super.baseMapper.insert(r);
        return ResultUtil.result(EnumCode.OK.getValue(), "Added successfully");
    }

    /**
     * @desc: delete role
     */
    @Transactional
    public Object delRole(String[] ids) {

        for (String id : ids) {
            super.baseMapper.deleteById(id);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "Deleted successfully");
    }

    /**
     * Bind Role drop-down box
     */
    public List<Role> findAllRoles() {
        return super.baseMapper.findAllRoles();
    }
}
