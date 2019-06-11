package com.qf.app.bean;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table
public class DevUser {

	@Id
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 账号
	 */
	@NotBlank(message = "用户名为必填项!")
	private String devUsername;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码为必填项!")
	private String devPassword;

	/**
	 * 盐
	 */
	private String devSalt;

	/**
	 * email
	 */
	@NotBlank(message = "邮箱为必填项!")
	private String devEmail;

	/**
	 * 邮件激活code
	 */
	private String devCode;

	/**
	 * 出生日期
	 */
	@NotNull(message = "出生年月为必填项!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date devBirthday;

	/**
	 * 0 - 账号锁定,  1 - 正常
	 */
	private Integer devState;

	/**
	 * 创建时间
	 */
	private java.util.Date created;

	/**
	 * 修改时间
	 */
	private java.util.Date updated;


}
