package com.itsure.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 对象存储
 * 
 * @author itsure
 * @email 494471788@qq.com
 * @date 2019-07-13 18:56:52
 */
@TableName("sys_oss")
public class Oss extends Model<Oss> {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	    @TableId(value="id", type= IdType.AUTO)
			private Integer id;
	/**
	 * 
	 */
	    @TableField("company")
			private String company;
	/**
	 * 
	 */
	    @TableField("end_point")
			private String endPoint;
	/**
	 * 
	 */
	    @TableField("access_id")
			private String accessId;
	/**
	 * 
	 */
	    @TableField("secret")
			private String secret;
	/**
	 * 
	 */
	    @TableField("oss_preffix")
			private String ossPreffix;
	/**
	 * 
	 */
	    @TableField("oss_bucket_name")
			private String ossBucketName;
	/**
	 * 
	 */
	    @TableField("status")
			private Integer status;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * 设置：
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	/**
	 * 获取：
	 */
	public String getEndPoint() {
		return endPoint;
	}
	/**
	 * 设置：
	 */
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	/**
	 * 获取：
	 */
	public String getAccessId() {
		return accessId;
	}
	/**
	 * 设置：
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**
	 * 获取：
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * 设置：
	 */
	public void setOssPreffix(String ossPreffix) {
		this.ossPreffix = ossPreffix;
	}
	/**
	 * 获取：
	 */
	public String getOssPreffix() {
		return ossPreffix;
	}
	/**
	 * 设置：
	 */
	public void setOssBucketName(String ossBucketName) {
		this.ossBucketName = ossBucketName;
	}
	/**
	 * 获取：
	 */
	public String getOssBucketName() {
		return ossBucketName;
	}
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}