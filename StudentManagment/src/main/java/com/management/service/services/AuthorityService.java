package com.management.service.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.Authority;

import java.util.List;
import java.util.Map;


public interface AuthorityService extends IService<Authority> {
    List<Authority> loadUserResources(Map map);
    /**
     * @desc: Query menu
     */
    List<Authority> findPermissionByPage(Page<Authority> page, String permsName);

    /**
     * @desc: Add menu
     */
    Object addPermissions(Authority vo);

    /**
     * @desc: Delete Menu
     */
    Object delPermis(String[] ids);

    /**
     * @desc: Query menu according to menu
     */
    List<Authority> findPermissionByName(String name);

    /**
     * @desc: Query the upper-level menu based on the parent id
     */
    List<Authority> findLastPermissionByType(String type);

    /**
     * @desc: Example Query all parent menu binding trees
     */
    List<Authority> findBasePermission();

    /**
     * Query records based on url
     */
    Integer findCountByUrl(String requestUrl);
}
