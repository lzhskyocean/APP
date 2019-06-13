package com.qf.app.view;


import lombok.Data;

/**
 *  映射app_maintain视图.
 */
@Data
public class AppMaintain {

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
