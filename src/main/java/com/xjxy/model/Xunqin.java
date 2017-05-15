package com.xjxy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjxy.utils.Globals;

public class Xunqin implements Serializable {
    private Integer id;

    private String logname;

    private String birthplace;

    private Integer age;
    @JsonFormat(pattern = Globals.DATE_FORMAT)
    private Date losttime;

    private Integer logtype;

    private Integer losttype;

    private String avatarurl;

    private String note;

    private String contact;//联系人

    private String mobile;

    private Integer status;//寻亲状态
    
    private Integer userId;
    
    @JsonFormat(pattern = Globals.DATE_FORMAT)  
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 临时属性
     */
    private String logtypeName;
    private String losttypeName;
    private String statusName;
    private List<Comment> comments = new ArrayList<Comment>();
    private User user;
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLogtypeName() {
		return logtypeName;
	}
	public void setLogtypeName(String logtypeName) {
		this.logtypeName = logtypeName;
	}

	public String getLosttypeName() {
		return losttypeName;
	}
	public void setLosttypeName(String losttypeName) {
		this.losttypeName = losttypeName;
	}
    public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
     * ---------------------------------------------------
     * @return
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname == null ? null : logname.trim();
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getLosttime() {
        return losttime;
    }

    public void setLosttime(Date losttime) {
        this.losttime = losttime;
    }

    public Integer getLogtype() {
        return logtype;
    }

    public void setLogtype(Integer logtype) {
        this.logtype = logtype;
    }

    public Integer getLosttype() {
        return losttype;
    }

    public void setLosttype(Integer losttype) {
        this.losttype = losttype;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        Xunqin other = (Xunqin) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLogname() == null ? other.getLogname() == null : this.getLogname().equals(other.getLogname()))
            && (this.getBirthplace() == null ? other.getBirthplace() == null : this.getBirthplace().equals(other.getBirthplace()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getLosttime() == null ? other.getLosttime() == null : this.getLosttime().equals(other.getLosttime()))
            && (this.getLogtype() == null ? other.getLogtype() == null : this.getLogtype().equals(other.getLogtype()))
            && (this.getLosttype() == null ? other.getLosttype() == null : this.getLosttype().equals(other.getLosttype()))
            && (this.getAvatarurl() == null ? other.getAvatarurl() == null : this.getAvatarurl().equals(other.getAvatarurl()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLogname() == null) ? 0 : getLogname().hashCode());
        result = prime * result + ((getBirthplace() == null) ? 0 : getBirthplace().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getLosttime() == null) ? 0 : getLosttime().hashCode());
        result = prime * result + ((getLogtype() == null) ? 0 : getLogtype().hashCode());
        result = prime * result + ((getLosttype() == null) ? 0 : getLosttype().hashCode());
        result = prime * result + ((getAvatarurl() == null) ? 0 : getAvatarurl().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
	@Override
	public String toString() {
		return "Xunqin [id=" + id + ", logname=" + logname + ", birthplace="
				+ birthplace + ", age=" + age + ", losttime=" + losttime
				+ ", logtype=" + logtype + ", losttype=" + losttype
				+ ", avatarurl=" + avatarurl + ", note=" + note + ", userId="
				+ userId + ", createTime=" + createTime + "]";
	}
    
    
}