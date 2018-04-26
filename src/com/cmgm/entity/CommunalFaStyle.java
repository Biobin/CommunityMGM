package com.cmgm.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Bio
 * @date   2018年3月26日
 * @time   下午11:16:57
 * 小区设施类型实体类:
 * 公共服务性设施（教育、医疗卫生、文化娱乐、交通、体育、社会福利与保障、行政管理与社区服务、邮政电信和商业金融服务等）
 * 共用基础设施（共用的上下水管道、落水管、水箱、加压水泵、电梯、天线、供电线路、照明用具、锅炉、暖气线路、煤气线路、
 * 		绿地、道路、路灯、沟、渠、池、井、非经营性车场车库、公益性文体设施和共用的设施设备使用的房屋等）
 * 消防设施
 * 私人设施
 *
 */

@Entity
@Table(name="CMGM_CommunalFaStyle")
public class CommunalFaStyle {

	private Integer id;
	private String name;
	private Set<CommunalFacilities> communalFacilities;
	
	public CommunalFaStyle() {
	}

	public CommunalFaStyle(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_communalFaStyle")
	@SequenceGenerator(name = "seq_communalFaStyle", sequenceName = "seq_communalFaStyle", allocationSize = 1, initialValue = 1)
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

	@OneToMany(mappedBy="communalFaStyle",fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	public Set<CommunalFacilities> getCommunalFacilities() {
		return communalFacilities;
	}

	public void setCommunalFacilities(Set<CommunalFacilities> communalFacilities) {
		this.communalFacilities = communalFacilities;
	}
	
}
