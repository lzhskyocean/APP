package com.qf.app.mapper;

import com.qf.app.bean.AppInfo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * APP详情表对应的mapper接口
 * @author 郑大仙丶
 * @date 2019-06-12 11:32
 */
public interface AppInfoMapper extends Mapper<AppInfo> {

    @Select("select software_name ,downloads from app_info order by downloads desc limit 5")
    List<AppInfo> findOrderByDownloadsDescLimit();


}
