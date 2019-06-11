package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table
public class RolesPerms {

	@Id
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 角色名称(字典表)
	 */
	private String roleName;

	/**
	 * 权限信息
	 */
	private String permName;

	/**
	 * 创建时间
	 */
	private java.util.Date created;

	/**
	 * 修改时间
	 */
	private java.util.Date updated;

	/**
	 * 角色id(字典表)
	 */
	private Integer roleId;


}
