package com.management.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.management.Parametric.Authority;
import com.management.mapper.Mapper.RoleAuthMapper;
import com.management.Parametric.RoleAuth;
import com.management.service.services.RoleAuthorityService;
import com.management.utils.EnumCode;
import com.management.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuth> implements RoleAuthorityService {

    /**
     * Querying Role Permissions by Role
     */
    public List<RoleAuth> findRolesAuthByRole(String roleId) {
        return baseMapper.findRolesPermisByRole(roleId);
    }

    /**
     * Adding Role Privileges
     */
    @Transactional
    public Object addRolesAuth(RoleAuth vo) {
        RoleAuth rp = null;
        String roleId = vo.getRoleId();
        String[] permisIds = vo.getPermsIds()==null?new String[0]:vo.getPermsIds();

        Map<String,Object> map = new HashMap<>();
        map.put("role_id",roleId);
        super.baseMapper.deleteByMap(map);

        for (int i = 0,j=permisIds.length; i < j; i++) {
            rp = new RoleAuth();
            rp.setRoleId(roleId);
            rp.setPermsId(permisIds[i]);
            super.baseMapper.insert(rp);
        }
        return ResultUtil.result(EnumCode.OK.getValue(), "保存成功");
    }

    /**
     * Query the number of records according to the role id
     */
    public Integer findCountByRole(String roleId,String url) {
        return super.baseMapper.findCountByRole(roleId, url);
    }

    /**
     * Query role menu by parent id \ user type
     */
    public List<Authority> findRolesPermisByFatherId(String fatherId, String roleId) {
        return super.baseMapper.findRolesPermisByFatherId(fatherId, roleId);
    }
}