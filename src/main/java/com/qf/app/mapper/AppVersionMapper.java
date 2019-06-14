package com.qf.app.mapper;

import com.qf.app.bean.AppVersion;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * APP版本表对应的接口
 * @author 郑大仙丶
 * @date 2019-06-14 17:25
 */
public interface AppVersionMapper extends Mapper<AppVersion> {


    // 查询最新的三个APP历史版本
    List<AppVersion> findByAppIdOrderByUpdatedDescLimit(@Param("appId")Long id);

}
