package com.msb.ngs.model.vcf;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.msb.ngs.model.user.User;

@Entity(name="vcf_files")
public class VcfFile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int fielId;
	private String name;
	private Date uploadTime;
	private Date updateTime;
	private String path;
	private User uploadedBy;
	
	
	
	public User getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(User uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public int getFielId() {
		return fielId;
	}
	public void setFielId(int fielId) {
		this.fielId = fielId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	@Override
	public String toString() {
		return "VcfFile [fielId=" + fielId + ", name=" + name + ", uploadTime=" + uploadTime + ", updateTime="
				+ updateTime + ", path=" + path + "]";
	}

	
	
	
}
