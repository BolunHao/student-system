package com.management.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.management.Parametric.Role;
import com.management.Parametric.RoleAuth;
import com.management.service.services.RoleAuthorityService;
import com.management.service.services.RoleService;
import com.management.utils.EnumCode;
import com.management.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleAuthorityService rolePermissionService;

    /**
     * @desc: Query Role
     */
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public Object findRoleByPage(Integer startPage,Integer pageSize,String roleName) {

        Page<Role> page = new Page<Role>(startPage,pageSize);
        List<Role> list = roleService.findRoleByPage(page,roleName);
        return ResultUtil.result(EnumCode.OK.getValue(),EnumCode.OK.getText(),list,page.getTotal());
    }

    /**
     * @desc: New Characters
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public Object addRoles(@Valid Role vo, BindingResult bindingResult) {
        return roleService.addRoles(vo);
    }

    /**
     * @desc: Delete Role
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Object delRoles(Role role) {
        String[] ids = role.getIds();
        if (null == ids || ids.length == 0) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        return roleService.delRole(ids);
    }

    /**
     * Querying Role Permissions by Role
     */
    @RequestMapping(value = "/findRolesPermisByRole",method = RequestMethod.GET)
    public Object findRolesPermisByRole(String roleId, String fatherId) {
        if (null == roleId) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
        }
        List<RoleAuth> list = rolePermissionService.findRolesPermisByFatherId(fatherId,roleId);
        String[] arr = new String[list.size()];
        for (int i = 0,j=list.size(); i < j; i++) {
            arr[i] = list.get(i).getPermsId();
        }
        return ResultUtil.result(EnumCode.OK.getValue(),EnumCode.OK.getText(), arr);
    }

    /**
     * Adding Role Privileges
     */
    @RequestMapping(value = "/perms",method = RequestMethod.POST)
    public Object addRolesPermis(RoleAuth vo, BindingResult bindingResult) {
        return rolePermissionService.addRolesAuth(vo);
    }

    /**
     * Bind Role drop-down box
     */
    @RequestMapping(value = "/findAllRoles",method = RequestMethod.GET)
    public Object findAllRoles(RoleAuth vo,BindingResult bindingResult) {
        List<Role> list = roleService.findAllRoles();
        return ResultUtil.result(EnumCode.OK.getValue(),EnumCode.OK.getText(),list);
    }

}

