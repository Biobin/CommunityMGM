package com.cmgm.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Bio
 * @date   2018年3月26日
 * @time   下午9:36:09
 * 用户实体类（用于登录）
 *
 */
@Entity
@Table(name="CMGM_USER")
public class User {

	private Integer id;
	private String username;
	private String password;
	private Role role;
	
	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_CMGMuser")
	@SequenceGenerator(name = "seq_CMGMuser", sequenceName = "seq_CMGMuser", allocationSize = 1, initialValue = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JoinColumn(name="roleId",foreignKey=@ForeignKey(name="fk_role"))
	@ManyToOne
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
