package com.cmgm.entity;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Bio
 * 角色实体类：区分不同角色
 * 暂定角色有：物业管理人员，业主，系统维护管理人员（大概是不需要的）
 *
 */
@Entity
@Table(name="CMGM_Role")
public class Role {
	
	private Integer id;
	private String name;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	private Set<User> users;
	private Set<Menu> menus;
	
	public Role() {
	}

	public Role(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role(Integer id, String name, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_CMGMrole")
	@SequenceGenerator(name = "seq_CMGMrole", sequenceName = "seq_CMGMrole", allocationSize = 1, initialValue = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@OneToMany(mappedBy="role",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	//使用 @ManyToMany 注解来映射多对多关联关系
	//使用 @JoinTable 来映射中间表
	//1. name 指向中间表的名字
	//2. joinColumns 映射当前类所在的表在中间表中的外键
	//2.1 name 指定外键列的列名
	//2.2 referencedColumnName 指定外键列关联当前表的哪一列
	//3. inverseJoinColumns 映射关联的类所在中间表的外键
	@JoinTable(name="cmgm_role_menu",
		joinColumns={@JoinColumn(name="roleId", referencedColumnName="id",foreignKey=@ForeignKey(name="fk_m_role"))},
		inverseJoinColumns={@JoinColumn(name="menuId", referencedColumnName="id",foreignKey=@ForeignKey(name="fk_r_menu"))})
	@ManyToMany //(fetch=FetchType.EAGER)
	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
}
