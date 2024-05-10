package com.management.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.management.Parametric.Authority;
import com.management.service.services.AuthorityService;
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
    @RequestMapping(value = "perms")
    public class AuthController  extends BaseController{

        @Autowired
        private AuthorityService permissionService;

        /**
         * @desc: Enquiry Menu
         */
        @RequestMapping(value = "/list" ,method = RequestMethod.GET)
        public Object findPermissionByPage(Integer startPage,Integer pageSize,String permsName) {

            Page<Authority> page = new Page<Authority>(startPage,pageSize);
            List<Authority> list = permissionService.findPermissionByPage(page,permsName);
            return ResultUtil.result(EnumCode.OK.getValue(),EnumCode.OK.getText(),list,page.getTotal());
        }

        /**
         * @desc: New menu
         */
        @RequestMapping(value = "/add" ,method = RequestMethod.POST)
        public Object addPermissions(@Valid Authority vo, BindingResult bindingResult) {
            return permissionService.addPermissions(vo);
        }

        /**
         * @desc: Delete menu
         */
        @RequestMapping(value = "/delete",method = RequestMethod.POST)
        public Object delPermis(Authority perms) {
            String[] ids = perms.getIds();
            if (null == ids || ids.length == 0) {
                return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(), EnumCode.BAD_REQUEST.getText());
            }
            return permissionService.delPermis(ids);
        }

        /**
         * @desc: Query all menus whose parent menu is 1
         */
        @RequestMapping(value = "/findLastPermissionByType" ,method = RequestMethod.GET)
        public Object findAllBasePermission(String type) {
            List<Authority> list = permissionService.findLastPermissionByType(type);
            if (null == list ||list.isEmpty()) {
                return ResultUtil.result(EnumCode.GONE.getValue(),"没有记录");
            }
            return ResultUtil.result(EnumCode.OK.getValue(),EnumCode.OK.getText(),list);
        }

        /**
         * @desc: Bind Tree Menu
         */
        @RequestMapping(value = "/findBasePermission" ,method = RequestMethod.GET)
        public Object findBasePermission() {
            List<Authority> list = permissionService.findBasePermission();
            return ResultUtil.result(EnumCode.OK.getValue(),EnumCode.OK.getText(),list);
        }
}
