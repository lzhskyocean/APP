package com.qf.app.mapper;

import com.qf.app.bean.BackendUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

/**
 * 后台管理的用户Mapper
 * @author 郑大仙丶
 * @date 2019-06-17 10:26
 */
public interface BackendUserMapper extends Mapper<BackendUser> {


    //1. 根据用户查询用户角色.
    @Select("select value_name from backend_user b,data_dictionary d where b.backend_type = d.value_id and d.type_code = 'USER_TYPE' and b.backend_code = #{backendCode}")
    Set<String> findRolesByBackendCode(@Param("backendCode")String backendCode);

    //2. 根据角色名称查询用户的权限.
    Set<String> findPermsByRoles(@Param("set") Set<String> roles);

}
