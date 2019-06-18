package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table
public class AppCategory {

	@Id
	/**
	 * 主键id
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "分类的id为必传项,岂能不传!")
	private Long id;

	/**
	 * 分类的名字
	 */
	@NotBlank(message = "分类的名称为必传项,岂能不填!")
	private String categoryName;

	/**
	 * 分类的父id
	 */
	private Long parentId;

	/**
	 * 是否是父节点
	 */
	private Boolean isParent;

	/**
	 * 创建时间
	 */
	private java.util.Date created;

	/**
	 * 修改时间
	 */
	private java.util.Date updated;


}
