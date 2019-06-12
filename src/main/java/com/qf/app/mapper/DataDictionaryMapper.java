package com.qf.app.mapper;

import com.qf.app.bean.DataDictionary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 对应字典表的dao接口
 * @author 郑大仙丶
 * @date 2019-06-12 16:27
 */
public interface DataDictionaryMapper extends Mapper<DataDictionary> {

    // 根据typeCode查询指定的数据
    @Select("select value_id,value_name from data_dictionary where type_code = #{typeCode}")
    List<DataDictionary> findByTypeCode(@Param("typeCode")String typeCode);
}
