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
@Table( name="USERS" )
public class User {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pass_hash")
	private String pass_hash;
	
	@Column(name="coins")
	private int coins;
	
	@Column(name="is_active")
	private int is_active;
	
	@Column(name="is_admin")
	private int is_admin;
	
	@Column(name="last_login")
	private String last_login;
	
	@Column(name="created")
	private String created;
	
	@Column(name="updated")
	private String updated;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserSetting> userSetting = new HashSet<UserSetting>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Vote> userVotes = new HashSet<Vote>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "contest")
	private Set<Vote> userVoteContest = new HashSet<Vote>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Contest> contest = new HashSet<Contest>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Announcement> announcements = new HashSet<Announcement>(0);
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass_hash() {
		return pass_hash;
	}
	public void setPass_hash(String pass_hash) {
		this.pass_hash = pass_hash;
	}
	
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
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
	
	public Set<Contest> getContest() {
		return contest;
	}
	public void setContest(Set<Contest> contest) {
		this.contest = contest;
	}
	
	public Set<Vote> getUserVotes() {
		return userVotes;
	}
	public void setUserVotes(Set<Vote> userVotes) {
		this.userVotes = userVotes;
	}
	
	public Set<Vote> getUserVoteContest() {
		return userVoteContest;
	}
	public void setUserVoteContest(Set<Vote> userVoteContest) {
		this.userVoteContest = userVoteContest;
	}
}