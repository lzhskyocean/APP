package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table
public class AppVersion {

	@Id
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 对应APP的主键
	 */
	private Long appId;

	/**
	 * 版本号
	 */
	private String versionNo;

	/**
	 * 版本信息
	 */
	private String versionInfo;

	/**
	 * 发布状态
	 */
	private Long publishStatus;

	/**
	 * apk安装包的下载链接.
	 */
	private String downloadLink;

	/**
	 * 版本大小
	 */
	private String versionSize;

	/**
	 * 当前版本APK文件名
	 */
	private String apkFileName;

	/**
	 * 创建时间
	 */
	private java.util.Date created;

	/**
	 * 修改时间
	 */
	private java.util.Date updated;


	/**
	 * 发布状态
	 */
	@Transient
	private String publishStatusName;

	/**
	 * 软件名称
	 */
	@Transient
	private String softwareName;









}
