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
 * 用户文章
 * 
 * @author itsure
 * @email 494471788@qq.com
 * @date 2019-07-08 13:01:19
 */
@TableName("sys_article")
public class Article extends Model<Article> {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	    @TableId(value="id", type= IdType.AUTO)
			private Integer id;
	/**
	 * 
	 */
	    @TableField("menu_id")
			private Integer menuId;
	/**
	 * 
	 */
	    @TableField("title")
			private String title;
	/**
	 * 
	 */
	    @TableField("content")
			private String content;
	/**
	 * 
	 */
	    @TableField("create_time")
		    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
		private Date createTime;
	/**
	 * 
	 */
	    @TableField("thumb")
			private String thumb;
	/**
	 * 
	 */
	    @TableField("origin_type")
			private Integer originType;
	/**
	 * 
	 */
	    @TableField("author")
			private String author;
	/**
	 * 
	 */
	    @TableField("hits")
			private Integer hits;
	/**
	 * 
	 */
	    @TableField("update_time")
		    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
		private Date updateTime;

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
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	/**
	 * 获取：
	 */
	public Integer getMenuId() {
		return menuId;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
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
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	/**
	 * 获取：
	 */
	public String getThumb() {
		return thumb;
	}
	/**
	 * 设置：
	 */
	public void setOriginType(Integer originType) {
		this.originType = originType;
	}
	/**
	 * 获取：
	 */
	public Integer getOriginType() {
		return originType;
	}
	/**
	 * 设置：
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	/**
	 * 获取：
	 */
	public Integer getHits() {
		return hits;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", menuId=" + menuId +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", createTime=" + createTime +
				", thumb='" + thumb + '\'' +
				", originType=" + originType +
				", author='" + author + '\'' +
				", hits=" + hits +
				", updateTime=" + updateTime +
				'}';
	}
}