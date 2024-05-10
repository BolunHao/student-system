package com.management.service.services;

import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.Authority;
import com.management.Parametric.RoleAuth;

import java.util.List;

public interface RoleAuthorityService extends IService<RoleAuth> {

    /**
     * Adding Role Privileges
     */
    Object addRolesAuth(RoleAuth vo);

    /**
     * Querying Role Permissions by Role
     */
    List<RoleAuth> findRolesAuthByRole(String roleId);

    /**
     * Query the number of records according to the role id
     */
    Integer findCountByRole(String roleId, String url);

    /**
     * Query role menu by parent id \ user type
     */
    List<Authority> findRolesPermisByFatherId(String fatherId, String roleId);
}
