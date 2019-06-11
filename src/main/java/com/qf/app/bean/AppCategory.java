package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table
public class AppCategory {

	@Id
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 分类的名字
	 */
	private String categoryName;

	/**
	 * 分类的父id
	 */
	private Long parentId;

	/**
	 * 是否是父节点
	 */
	private String isParent;

	/**
	 * 创建时间
	 */
	private java.util.Date created;

	/**
	 * 修改时间
	 */
	private java.util.Date updated;


}
