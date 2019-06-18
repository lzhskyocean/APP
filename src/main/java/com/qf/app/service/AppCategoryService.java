package com.qf.app.service;

import com.qf.app.bean.AppCategory;
import com.qf.app.vo.LayUITreeVO;

import javax.validation.Valid;
import java.util.List;

/**
 * app分类表的service
 * @author 郑大仙丶
 * @date 2019-06-12 17:03
 */
public interface AppCategoryService {
    /**
     * 根据父id查询分类
     * @param parentId
     *  父id
     * @return
     */
    List<AppCategory> findByParentId(long parentId);

    /**
     * 查询全部的分类数据,在layUI的树组件中展示.
     * @return
     */
    List<LayUITreeVO> findAllCategoryForLayUITree();

    /**
     * 添加APP分类.
     * @param appCategory
     * @return
     */
    Long add(AppCategory appCategory);

    /**
     * 重命名APP分类.
     * @param appCategory
     */
    void update(AppCategory appCategory);

    /**
     * 删除APP分类.
     * @param id
     */
    void delete(Long id);
}
