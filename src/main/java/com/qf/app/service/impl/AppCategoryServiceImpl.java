package com.qf.app.service.impl;

import com.qf.app.bean.AppCategory;
import com.qf.app.mapper.AppCategoryMapper;
import com.qf.app.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 郑大仙丶
 * @date 2019-06-12 17:03
 */
@Service
public class AppCategoryServiceImpl implements AppCategoryService {

    @Autowired
    private AppCategoryMapper appCategoryMapper;

    @Override
    public List<AppCategory> findByParentId(long parentId) {
        return appCategoryMapper.findByParentId(parentId);
    }
}
