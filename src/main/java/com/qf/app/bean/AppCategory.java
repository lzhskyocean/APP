package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table
public class AppCategory {

	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		set.add(null);


		Map<String,String> map = new HashMap<>();
		map.put(null,"123123");
	}


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
