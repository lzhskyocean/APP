package com.qf.app.mapper;

import com.qf.app.bean.AppCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 郑大仙丶
 * @date 2019-06-12 16:57
 */
public interface AppCategoryMapper extends Mapper<AppCategory> {

    // 根据父id查询对应分类.
    @Select("select id,category_name from app_category where parent_id = #{parentId}")
    List<AppCategory> findByParentId(@Param("parentId")Long parentId);

}
