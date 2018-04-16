package com.cmgm.VO;
/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:50:16
 * 报修信息VO类
 */

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MaintenanceVO {

	private Integer id;
	private String code;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Integer ownerId;
	private String ownerName;
	private String ownerPhone;
	private String ownerEmail;
	private String details;
	//下拉列表多选，选取公共设施类型，然后再下拉中选取具体公共设施或私人设施，均为多选
	private Set<Integer> communalFacilitiesIds = new HashSet<>();
	private Set<String> communalFacilitiesNames = new HashSet<>();
	private Set<Integer> communalFaStyleId;
	private Set<String> communalFaStyleName;
	
}
