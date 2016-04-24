package com.pwp.restapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table( name="SETTINGS" )
public class Setting {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="setting")
	private String setting;
	
	@Column(name="created")
	private String created;
	
	@Column(name="updated")
	private String updated;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "setting")
	private
	Set<UserSetting> userSetting = new HashSet<UserSetting>(0);

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSetting() {
		return setting;
	}
	public void setSetting(String setting) {
		this.setting = setting;
	}
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	public Set<UserSetting> getUserSetting() {
		return userSetting;
	}
	public void setUserSetting(Set<UserSetting> userSetting) {
		this.userSetting = userSetting;
	}
}