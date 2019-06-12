package com.qf.app.service.impl;

import com.qf.app.bean.DataDictionary;
import com.qf.app.mapper.DataDictionaryMapper;
import com.qf.app.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表对应的service
 * @author 郑大仙丶
 * @date 2019-06-12 16:40
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;


    /**
     * 根据typeCode查询字典表数据(value_id和value_name)
     * @param typeCode
     * @return
     */
    @Override
    public List<DataDictionary> findByTypeCode(String typeCode) {
        return dataDictionaryMapper.findByTypeCode(typeCode);
    }
}
