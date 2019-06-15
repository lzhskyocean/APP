package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table
public class AppVersion {

	@Id
	/**
	 * 主键id
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 对应APP的主键
	 */
	@NotNull(message = "没有指定当前版本对应的软件!")
	private Long appId;

	/**
	 * 版本号
	 */
	@NotBlank(message = "版本号为必填项,岂能不写?")
	private String versionNo;

	/**
	 * 版本信息
	 */
	@NotBlank(message = "版本信息为必填项,岂能不写?")
	private String versionInfo;

	/**
	 * 发布状态
	 */
	private Long publishStatus;

	/**
	 * apk安装包的下载链接.
	 */
	@NotBlank(message = "apk文件为必传项,传一个,兄弟!?")
	private String downloadLink;

	/**
	 * 版本大小
	 */
	@NotBlank(message = "版本大小为必填项,岂能不写?")
	private String versionSize;

	/**
	 * 当前版本APK文件名
	 */
	@NotBlank(message = "apk文件为必传项,传一个,兄弟!?")
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
