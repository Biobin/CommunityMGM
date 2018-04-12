/**
 * 车辆信息js,业主信息js
 */
$(function(){
	
	$('#carId').combobox({
		valueField : 'id',
		textField : 'plateNumber',
		limitToList : true,
		url : basePath+'/car/plateNumberList',
		onChange:function(newValue,oldValue) {
				$("#car_form").form('load', basePath+ '/car/showCarInfo/'+newValue);
		}
	});

	if(roleId==2) {
		$("#owner_form").form('load', basePath+ '/owner/showOwnerInfo');
	}
	
});	