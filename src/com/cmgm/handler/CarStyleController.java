package com.cmgm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.common.StringUtils;
import com.cmgm.entity.CarStyle;
import com.cmgm.service.CarStyleService;

/**
 *
 * @author Bio
 * @date 2018年4月2日
 * @time 下午3:55:16
 *
 */

@Controller
public class CarStyleController {

	@Autowired
	private CarStyleService carStyleService;
	
	@RequestMapping("/carStyle/carStyleManage")
	public String carStyleManage(){
		return "carStyleManage";
	};
	
	@ResponseBody
	@RequestMapping(value="/carStyle/getCarStyle", method=RequestMethod.POST)
	public Map<String, Object> getCarStyles(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));// 当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));// 每页行数
		List<CarStyle> carStyles = carStyleService.getCarStyle(pageNO,pageSize);
		int count = carStyleService.getCountCarStyle();
		if (carStyles == null || carStyles.isEmpty()) {
			carStyles = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", carStyles);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/carStyle/addOrUpdateCarStyle", method=RequestMethod.POST)
	public CarStyle addOrUpdateCarStyle(HttpServletRequest request) {
		String name = request.getParameter("name");
		String idString = request.getParameter("id");
		CarStyle carStyle = null;
		if (idString != null && !idString.equals("")) {
			carStyle = carStyleService.getCarStyleById(StringUtils.getInteger(idString));
		} else {
			carStyle = new CarStyle();
		}
		carStyle.setName(name);
		return carStyleService.addOrUpdateCarStyle(carStyle);
	}
	
	@ResponseBody
	@RequestMapping("/carStyle/deleteCarStyle")
	public String deleteCarStyle(Integer id) {
		String cadelete = "";
		if (id != null) {
			cadelete = carStyleService.deleteCarStyle(id);
		}
		return cadelete;
	}
	
}
