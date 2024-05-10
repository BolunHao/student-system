package com.management.mapper.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.management.Parametric.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper extends BaseMapper<Authority> {

    /**
     * @desc: Enquiry Menu
     */
    List<Authority> findPermissionByPage(Pagination page, @Param("permsName") String permsName);

    /**
     * @desc: Search menu by menu
     */
    List<Authority> findPermissionByName(@Param("name") String name);

    /**
     * @desc: Query all parent menu bound dropdowns
     */
    List<Authority> findLastPermissionByType(@Param("type") String type);

    /**
     * @desc: Query all parent menu bindings tree
     */
    List<Authority> findBasePermission();

    /**
     * @desc: Query menu by parent id
     */
    List<Authority> findPermissionByFatherId(@Param("fatherId") String fatherId);

    /**
     * Query records according to url
     */
    Integer findCountByUrl(@Param("requestUrl") String requestUrl);
}
