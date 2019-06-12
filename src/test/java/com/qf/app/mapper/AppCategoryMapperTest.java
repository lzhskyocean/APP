package com.qf.app.mapper;

import com.qf.app.AppApplicationTests;
import com.qf.app.bean.AppCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 郑大仙丶
 * @date 2019-06-12 16:57
 */
public class AppCategoryMapperTest extends AppApplicationTests {

    @Autowired
    private AppCategoryMapper appCategoryMapper;


    @Test
    public void findByParentId(){
        Long parentId = 0L;

        List<AppCategory> list = appCategoryMapper.findByParentId(parentId);

        for (AppCategory appCategory : list) {
            System.err.println(appCategory);
        }

    }

}