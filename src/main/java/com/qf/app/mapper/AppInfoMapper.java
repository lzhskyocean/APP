package com.qf.app.mapper;

import com.qf.app.bean.AppInfo;
import com.qf.app.form.AppInfoMaintainForm;
import com.qf.app.view.AppMaintain;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * APP详情表对应的mapper接口
 * @author 郑大仙丶
 * @date 2019-06-12 11:32
 */
public interface AppInfoMapper extends Mapper<AppInfo> {

    @Select("select software_name ,downloads from app_info order by downloads desc limit 5")
    List<AppInfo> findOrderByDownloadsDescLimit();

    /**
     * 根据页面的条件,查询appmaintian视图中滴数据.
     * @param appInfoMaintainForm
     * @return
     */
    List<AppMaintain> findByCondition(AppInfoMaintainForm appInfoMaintainForm);


    // 根据id使用in去查询app的名称和状态
    List<AppInfo> findByIdIn(List<Long> ids);


    // 根据id使用in去修改app的状态
    // param -> ids      param -> status
    Integer updateAppStatusByIdIn(Map<String,Object> param);

}
