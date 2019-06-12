package com.qf.app.service;

import com.qf.app.bean.DataDictionary;

import java.util.List;

/**
 * 字典表对应的service
 * @author 郑大仙丶
 * @date 2019-06-12 16:39
 */
public interface DataDictionaryService {
    /**
     * 根据typeCode查询字典表数据(value_id和value_name)
     * @param typeCode
     * @return
     */
    List<DataDictionary> findByTypeCode(String typeCode);
}
