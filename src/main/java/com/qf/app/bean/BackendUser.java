package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table
public class BackendUser {

	@Id
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 用户账号
	 */
	private String backendCode;

	/**
	 * 用户密码
	 */
	private String backendPassword;

	/**
	 * 盐
	 */
	private String backendSalt;

	/**
	 * 真实姓名
	 */
	private String backendName;

	/**
	 * 用户类型
	 */
	private Long backendType;

	/**
	 * 创建时间
	 */
	private java.util.Date created;

	/**
	 * 修改时间
	 */
	private java.util.Date updated;


}
