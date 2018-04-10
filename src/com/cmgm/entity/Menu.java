package com.cmgm.entity;

import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午10:38:33
 * 菜单实体类
 */

@Entity
@Table(name="CMGM_MENU")
public class Menu {

	@JsonProperty(value="menuId")
	private Integer id;
	private String text;
	private State state;
	private String iconCls;
	private String url;
	private Set<Role> roles = new HashSet<>();	//物业管理人员为1，业主为2，超级管理员暂时不用
	private Menu parent;	//父节点
	@JsonIgnoreProperties(value={ "children", "parent" })
	private Set<Menu> children = new HashSet<>();//子节点
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cmgm_menu")
	@SequenceGenerator(name = "seq_cmgm_menu", sequenceName = "seq_cmgm_menu", allocationSize = 1, initialValue = 1)	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},  
            fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="stateId", foreignKey=@ForeignKey(name="state_Menu_Id"))
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public String getIconCls() {
		return iconCls;
	}
	
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
//	@OneToMany(cascade={CascadeType.REMOVE},mappedBy="menu")		//(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
//	@JoinColumn(name="roleId", foreignKey=@ForeignKey(name="role_Menu_Id"))
	@JoinTable(name="cmgm_role_menu",
		joinColumns={@JoinColumn(name="menuId", referencedColumnName="id",foreignKey=@ForeignKey(name="fk_r_menu"))},
		inverseJoinColumns={@JoinColumn(name="roleId", referencedColumnName="id",foreignKey=@ForeignKey(name="fk_m_role"))})
	@ManyToMany(fetch=FetchType.LAZY)
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@JsonIgnore
	@JoinColumn(name="pId", foreignKey=@ForeignKey(name="fk_p_menu"))
	@ManyToOne
	public Menu getParent() {
		return parent;
	}
	
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "parent")
	public Set<Menu> getChildren() {
		return children;
	}
	
	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	public Menu() {
	}

	public Menu(Integer id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

}
