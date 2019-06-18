package com.qf.app.view;


import lombok.*;
import javax.persistence.*;

/**
 * 映射app_maintian视图
 */
@Data
@Entity
@Table
public class AppMaintain {

	@Id
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 软件名称
	 */
	private String softwareName;

	/**
	 * 软件大小(单位:M)
	 */
	private String softwareSize;

	/**
	 * 下载次数
	 */
	private Long downloads;

	/**
	 * 软件简介
	 */
	private String appInfo;

	/**
	 * apk安装包的名字
	 */
	private String apkName;

	/**
	 * 界面语言
	 */
	private String interfaceLanguage;

	/**
	 * 图片访问路径
	 */
	private String picPath;

	/**
	 * 支持的ROM版本
	 */
	private String supportRom;

	/**
	 * 映射不用类型的id
	 */
	private Long appStatus;

	/**
	 * 状态名字
	 */
	private String appStatusName;

	/**
	 * 映射不用类型的id
	 */
	private Long appFlatform;

	/**
	 * 状态名字
	 */
	private String appFlatformName;

	/**
	 * 主键id
	 */
	private Long categoryLevel1;

	/**
	 * 分类的名字
	 */
	private String categoryLevel1Name;

	/**
	 * 主键id
	 */
	private Long categoryLevel2;

	/**
	 * 分类的名字
	 */
	private String categoryLevel2Name;

	/**
	 * 主键id
	 */
	private Long categoryLevel3;

	/**
	 * 分类的名字
	 */
	private String categoryLevel3Name;

	/**
	 * 版本号
	 */
	private String versionNo;


}
