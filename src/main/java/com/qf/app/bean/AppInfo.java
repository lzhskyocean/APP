package com.qf.app.bean;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table
public class AppInfo {

	@Id
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 软件名称
	 */
	@NotBlank(message = "软件名称不能为空!")
	private String softwareName;

	/**
	 * apk安装包的名字
	 */
	@NotBlank(message = "apk名称不能为空!")
	private String apkName;

	/**
	 * 支持的ROM版本
	 */
	@NotBlank(message = "ROM版本不能为空!")
	private String supportRom;

	/**
	 * 界面语言
	 */
	@NotBlank(message = "界面语言不能为空!")
	private String interfaceLanguage;

	/**
	 * 软件大小(单位:M)
	 */
	@NotBlank(message = "软件大小不能为空!")
	private String softwareSize;

	/**
	 * 软件简介
	 */
	@NotBlank(message = "软件简介不能为空!")
	private String appInfo;

	/**
	 * APP的状态:待审核 , 审核通过 , 审核驳回 , 上架 , 下架.
	 */
	private Long appStatus;

	/**
	 * 上架时间
	 */
	private java.util.Date onSaleDate;

	/**
	 * 下架时间
	 */
	private java.util.Date offSaleDate;

	/**
	 * APP的所属平台:手机 , 平板 , 通用
	 */
	@NotNull(message = "所属平台不能为空!")
	private Long flatformId;

	/**
	 * 下载次数
	 */
	private Long downloads;

	/**
	 * 一级分类
	 */
	@NotNull(message = "一级分类不能为空!")
	private Long categoryLevel1;

	/**
	 * 二级分类
	 */
	@NotNull(message = "二级分类不能为空!")
	private Long categoryLevel2;

	/**
	 * 三级分类
	 */
	@NotNull(message = "三级分类不能为空!")
	private Long categoryLevel3;

	/**
	 * 录入者,关联dev_user表的主键
	 */
	private Long devId;

	/**
	 * 图片访问路径
	 */
	@NotBlank(message = "上传图片不能为空!")
	private String picPath;

	/**
	 * 版本id,关联app_version的主键
	 */
	private Long versionId;

	/**
	 * 创建时间
	 */
	private java.util.Date created;

	/**
	 * 修改时间
	 */
	private java.util.Date updated;


}
