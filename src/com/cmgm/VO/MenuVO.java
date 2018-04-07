package com.cmgm.VO;
/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午10:49:28
 * 菜单VO类
 */
public class MenuVO {

	private Integer id;
	private String text;
	private Integer stateId;	//节点状态：open、closed
	private String stateName;
	private String url;			//树节点指向的连接
	private String iconCls;		//树节点前显示的图标
	private Integer sortNum;	//用于目录树排序
	private Integer pid; //指向父节点id
	private boolean checked; // 节点是否选中
	
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
	
	public Integer getStateId() {
		return stateId;
	}
	
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getIconCls() {
		return iconCls;
	}
	
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}
	
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	public Integer getPid() {
		return pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
