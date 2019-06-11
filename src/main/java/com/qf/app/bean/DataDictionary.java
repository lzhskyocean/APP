package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table
public class DataDictionary {

	@Id
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 对应不同的类型. 
	 */
	private String typeCode;

	/**
	 * 对应不同类型的名字
	 */
	private String typeName;

	/**
	 * 映射不用类型的id
	 */
	private Long valueId;

	/**
	 * 状态名字
	 */
	private String valueName;

	/**
	 * 创建时间
	 */
	private java.util.Date created;

	/**
	 * 修改时间
	 */
	private java.util.Date updated;


}
