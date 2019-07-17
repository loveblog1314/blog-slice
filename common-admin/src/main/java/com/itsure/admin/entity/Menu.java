package com.itsure.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 前台菜单
 * 
 * @author itsure
 * @email 494471788@qq.com
 * @date 2019-07-07 12:32:42
 */
@TableName("sys_menu")
public class Menu extends Model<Menu> {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	    @TableId(value="id", type= IdType.AUTO)
			private Integer id;
	/**
	 * 
	 */
	    @TableField("name")
			private String name;
	/**
	 * 
	 */
	    @TableField("desc")
			private String desc;
	/**
	 * 
	 */
	    @TableField("update_time")
		    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
		private Date updateTime;
	/**
	 * 
	 */
	    @TableField("create_time")
		    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
		private Date createTime;
	/**
	 * 
	 */
	    @TableField("parent_id")
			private Integer parentId;

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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public Integer getParentId() {
		return parentId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}