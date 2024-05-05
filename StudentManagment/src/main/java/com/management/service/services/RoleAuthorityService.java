package com.management.service.services;

import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.Authority;
import com.management.Parametric.RoleAuth;

import java.util.List;

public interface RoleAuthorityService extends IService<RoleAuth> {

    /**
     * Add role Permissions
     */
    Object addRolesPermis(RoleAuth vo);

    /**
     * Query role rights by role
     */
    List<RoleAuth> findRolesPermisByRole(String roleId);

    /**
     * Query the number of records based on the role id
     */
    Integer findCountByRole(String roleId, String url);

    /**
     * Query the role menu according to the parent id\ user type
     */
    List<Authority> findRolesPermisByFatherId(String fatherId, String roleId);
}
