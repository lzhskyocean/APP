package com.qf.app.service;

import com.qf.app.bean.AppCategory;

import java.util.List;

/**
 * app分类表的service
 * @author 郑大仙丶
 * @date 2019-06-12 17:03
 */
public interface AppCategoryService {
    /**
     * 根据父id查询分类
     * @param parent_id
     * @return
     */
    List<AppCategory> findByParentId(long parentId);
}
