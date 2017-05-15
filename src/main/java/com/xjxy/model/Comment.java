package com.xjxy.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjxy.utils.Globals;

/**
 * 评论实体类
 * @author wyy
 * 2017年4月19日
 *
 */
public class Comment implements Serializable {
    private Integer id;

    private String title;

    private Integer xunqinId;

    private Integer userId;
    @JsonFormat(pattern = Globals.DATE_FORMAT)
    private Date createTime;

    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * 临时变量
     */
    private User user;
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getXunqinId() {
        return xunqinId;
    }

    public void setXunqinId(Integer xunqinId) {
        this.xunqinId = xunqinId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getXunqinId() == null ? other.getXunqinId() == null : this.getXunqinId().equals(other.getXunqinId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getXunqinId() == null) ? 0 : getXunqinId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

	@Override
	public String toString() {
		return "Comment [id=" + id + ", title=" + title + ", xunqinId="
				+ xunqinId + ", userId=" + userId + ", createTime="
				+ createTime + ", content=" + content + "]";
	}
    
    
    
    
}