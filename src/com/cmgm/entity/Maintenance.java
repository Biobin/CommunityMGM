package com.cmgm.entity;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Bio
 * 报修信息实体类
 *
 */

@Entity
@Table(name="CMGM_Maintenance")
public class Maintenance {

	//提交前（业主填写）
	private Integer id;
	private String code;	//报修单号
//	private String name;
	private LocalTime createTime;
	private Owner owner;	//提交报修的业主
	private String details;	//报修信息细节
	private Set<CommunalFacilities> communalFacilities;	//损害的公共设施
	
	//提交后（物业填写）
	private String repairPersonnel;		//维修人员
	private String repairPerPhone;		//维修人员电话
	private LocalTime repairTime;		//维修时间
	private String repairRemarks;		//维修后备注
	private State state;
	
	public Maintenance() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_maintenance")
	@SequenceGenerator(name = "seq_maintenance", sequenceName = "seq_maintenance", allocationSize = 1, initialValue = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalTime createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},  
            fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="ownerId", foreignKey=@ForeignKey(name="owner_maintenance_Id"))
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@OneToMany(mappedBy="propertyManager",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<CommunalFacilities> getCommunalFacilities() {
		return communalFacilities;
	}

	public void setCommunalFacilities(Set<CommunalFacilities> communalFacilities) {
		this.communalFacilities = communalFacilities;
	}

	public String getRepairPersonnel() {
		return repairPersonnel;
	}

	public void setRepairPersonnel(String repairPersonnel) {
		this.repairPersonnel = repairPersonnel;
	}

	public String getRepairPerPhone() {
		return repairPerPhone;
	}

	public void setRepairPerPhone(String repairPerPhone) {
		this.repairPerPhone = repairPerPhone;
	}

	public LocalTime getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(LocalTime repairTime) {
		this.repairTime = repairTime;
	}

	public String getRepairRemarks() {
		return repairRemarks;
	}

	public void setRepairRemarks(String repairRemarks) {
		this.repairRemarks = repairRemarks;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},  
            fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="stateId", foreignKey=@ForeignKey(name="state_maintenance_Id"))
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
