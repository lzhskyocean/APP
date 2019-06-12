package com.qf.app.mapper;

import com.qf.app.AppApplicationTests;
import com.qf.app.bean.DataDictionary;
import com.qf.app.enums.TypeCodeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-12 16:30
 */
public class DataDictionaryMapperTest extends AppApplicationTests {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Test
    public void findByTypeCode() {
        List<DataDictionary> list = dataDictionaryMapper.findByTypeCode(TypeCodeEnum.APP_STATUS.getTypeCode());

        for (DataDictionary dataDictionary : list) {
            System.err.println(dataDictionary);
        }
    }
}