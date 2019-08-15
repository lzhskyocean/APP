package com.qf.app.service.impl;

import com.qf.app.bean.AppCategory;
import com.qf.app.exception.AppException;
import com.qf.app.mapper.AppCategoryMapper;
import com.qf.app.service.AppCategoryService;
import com.qf.app.vo.LayUITreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *
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


    //查询全部的分类数据,在layUI的树组件中展示.
    @Override
    public List<LayUITreeVO> findAllCategoryForLayUITree() {
        //1. 声明最外层返回结果.  List<LayUITreeVO>
        List<LayUITreeVO> treeVO1 = new ArrayList<>();
        //2. 查询一级分类内容.  List<AppCategory>
        List<AppCategory> categoryList1 = appCategoryMapper.findByParentId(0L);
        //3. 将一级分类内容导入到声明最外层返回结果
        for (AppCategory category1 : categoryList1) {
            //3.1 导入id和title.(前端要求的字段是id和title)
            LayUITreeVO vo1 = new LayUITreeVO(category1.getId(),category1.getCategoryName());
            //4.1 声明中间层的返回结果.  List<LayUITreeVO>
            List<LayUITreeVO> treeVO2 = new ArrayList<>();
            //4.2 根据一级分类的id,查询对应的二级分类. List<AppCategory>
            List<AppCategory> categoryList2 = appCategoryMapper.findByParentId(category1.getId());
            for (AppCategory category2 : categoryList2) {
                //4.3 导入id和title. 二级分类.
                LayUITreeVO vo2 = new LayUITreeVO(category2.getId(),category2.getCategoryName());
                //5.1 声明最内层的返回结果.  List<LayUITreeVO>
                List<LayUITreeVO> treeVO3 = new ArrayList<>();
                //5.2 根据二级分类的id,查询对应的三级分类. List<AppCategory>
                List<AppCategory> categoryList3 = appCategoryMapper.findByParentId(category2.getId());
                for (AppCategory category3 : categoryList3) {
                    //5.3 导入id和title. 三级分类(三级分类没有下一级,故不用添加子节点)
                    LayUITreeVO vo3 = new LayUITreeVO(category3.getId(),category3.getCategoryName());
                    treeVO3.add(vo3);
                }
                //4.4 导入children数据.
                vo2.setChildren(treeVO3);  // 添加子节点
                treeVO2.add(vo2);
            }
            //3.2 导入children数据.
            vo1.setChildren(treeVO2);
            treeVO1.add(vo1);
        }
        // 返回数据.
        return treeVO1;
    }


    //添加APP分类.
    @Override
    @Transactional
    public Long add(AppCategory appCategory) {
        // 默认为叶子节点.
        appCategory.setIsParent(false);
        //1. 执行添加
        appCategoryMapper.insertSelective(appCategory);

        //2. 查询当前叶子节点对应的父节点信息.
        AppCategory fatherNode = appCategoryMapper.selectByPrimaryKey(appCategory.getParentId());

        //3. 判断是否为父节点. -> 如果不是父节点,修改成父节点.
        if(!fatherNode.getIsParent()){
            // 是一个叶子节点,修改成父节点.
            fatherNode.setIsParent(true);
            // 执行修改
            appCategoryMapper.updateByPrimaryKeySelective(fatherNode);
        }

        // 返回自增后的主键值.
        return appCategory.getId();
    }


    // 重命名APP分类.
    @Override
    @Transactional
    public void update(AppCategory appCategory) {
        //1. 修改.
        int count = appCategoryMapper.updateByPrimaryKeySelective(appCategory);
        //2. 判断是否成功,失败,抛出异常
        if(count != 1){
            throw new AppException("重命名APP分类失败!");
        }
    }

    // 删除分类
    @Override
    @Transactional
    public void delete(Long id) {
        //1.1 查询当前节点信息. (获得当前节点的parentId.)
        AppCategory currentNode = appCategoryMapper.selectByPrimaryKey(id);
        // 递归删除当前节点及其全部的子节点.
        this.deleteById(id);
        //1.2 根据parentId查询当前节点的父节点的全部子节点
        AppCategory param = new AppCategory();
        param.setParentId(currentNode.getParentId());
        List<AppCategory> sonNode = appCategoryMapper.select(param);
        //1.4 如果没有,将当前节点的父节点的isParent修改成false.
        if(sonNode != null && sonNode.size() == 0) {
            AppCategory fatherNode = new AppCategory();
            fatherNode.setId(currentNode.getParentId());
            fatherNode.setIsParent(false);
            int count = appCategoryMapper.updateByPrimaryKeySelective(fatherNode);
            if(count != 1){
                throw new AppException("修改父节点信息失败!");
            }
        }
    }

    private void deleteById(Long id) {
        //1. 查询当前节点下的子节点.
        AppCategory param = new AppCategory();
        param.setParentId(id);
        List<AppCategory> sonNode = appCategoryMapper.select(param);
        //2. 如果子节点中有内容.
        if(sonNode != null && sonNode.size() > 0){
            // 递归删除全部的子节点.
            for (AppCategory appCategory : sonNode) {
                this.deleteById(appCategory.getId());
            }
        }
        // 删除当前节点,并返回.
        int count = appCategoryMapper.deleteByPrimaryKey(id);
        if(count != 1){
            throw new AppException("删除当前节点失败!");
        }
    }


}
