package com.qf.app.mapper;

import com.qf.app.bean.DevUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 郑大仙丶
 * @date 2019-06-11 12:14
 */
public interface DevUserMapper extends Mapper<DevUser> {

    @Select("select * from dev_user where dev_code is not null and created < now() - interval 3 day;")
    List<DevUser> findThreeDayNotActive();

    // 根据id删除用户
    Integer deleteByIds(List<Long> ids);
}
