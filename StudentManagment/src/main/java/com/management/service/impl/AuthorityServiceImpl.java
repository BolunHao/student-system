package com.management.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Authority;
import com.management.mapper.Mapper.AuthMapper;
import com.management.service.services.AuthorityService;
import com.management.utils.EnumCode;
import com.management.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthMapper, Authority> implements AuthorityService {


    public List<Authority> loadUserResources(Map map){
        return null;
    }

    /**
     * @desc: Enquiry Menu
     */
    public List<Authority> findPermissionByPage(Page<Authority> page,String dto) {
        return super.baseMapper.findPermissionByPage(page,dto);
    }

    /**
     * @desc: New menu
     */
    @Transactional
    public Object addPermissions(Authority vo) {

        Authority p = new Authority();
        p.setId(UUID.randomUUID().toString());
        p.setName(vo.getName());
        p.setUrl(vo.getUrl());
        p.setType(vo.getType());
        p.setParentId(vo.getParentId());
        baseMapper.insert(p);
        return ResultUtil.result(EnumCode.OK.getValue(), "新增成功");

    }

    /**
     * @desc: Deleting the menu (should also delete the relevant records in the t_role_perms table).
     */
    @Transactional
    public Object delPermis(String[] ids) {

        for (String id : ids) {
            super.baseMapper.deleteById(id);


        }
        return ResultUtil.result(EnumCode.OK.getValue(), "删除成功");
    }

    /**
     * @desc: Search menu by menu
     */
    public List<Authority> findPermissionByName(String name) {
        return super.baseMapper.findPermissionByName(name);
    }

    /**
     * @desc: Query parent menu by parent id
     */
    public List<Authority> findLastPermissionByType(String type) {
        return super.baseMapper.findLastPermissionByType(type);
    }

    /**
     * @desc: Query all parent menu bindings tree
     */
    public List<Authority> findBasePermission() {
        List<Authority> list = super.baseMapper.findBasePermission();
        if (null != list && !list.isEmpty()) {
            for (int i = 0,j = list.size();i< j;i++) {
                List<Authority> children = super.baseMapper.findPermissionByFatherId(list.get(i).getId());
                if (null != children && !children.isEmpty()) {
                    list.get(i).setChildren(children);
                    for (int i1 = 0, j1 = children.size();i1 < j1; i1++) {
                        List<Authority> children1 = super.baseMapper.findPermissionByFatherId(children.get(i1).getId());
                        if (null != children1 && !children1.isEmpty()) {
                            children.get(i1).setChildren(children1);
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * Query records according to url
     */
    public Integer findCountByUrl(String requestUrl) {
        return super.baseMapper.findCountByUrl(requestUrl);
    }
}

