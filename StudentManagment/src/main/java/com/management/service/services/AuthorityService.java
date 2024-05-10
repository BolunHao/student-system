package com.management.service.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.Authority;

import java.util.List;
import java.util.Map;


public interface AuthorityService extends IService<Authority> {
    List<Authority> loadUserResources(Map map);
    /**
     * @desc: Enquiry Menu
     */
    List<Authority> findPermissionByPage(Page<Authority> page, String permsName);

    /**
     * @desc: New menu
     */
    Object addPermissions(Authority vo);

    /**
     * @desc: Delete menu
     */
    Object delPermis(String[] ids);

    /**
     * @desc: Search menu by menu
     */
    List<Authority> findPermissionByName(String name);

    /**
     * @desc: Query parent menu by parent id
     */
    List<Authority> findLastPermissionByType(String type);

    /**
     * @desc: Query all parent menu bindings tree
     */
    List<Authority> findBasePermission();

    /**
     * Query records according to url
     */
    Integer findCountByUrl(String requestUrl);
}
