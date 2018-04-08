package com.cmgm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.VO.CarVO;
import com.cmgm.common.StringUtils;
import com.cmgm.service.CarService;

/**
 *
 * @author Bio
 * @date   2018年4月8日
 * @time   上午1:01:29
 * 车辆管理
 */

@Controller
public class CarController {

	@Autowired
	private CarService carService;
	
	@RequestMapping("/car/carManage")
	public String carManage() {
		return "carManage";
	}
	
	@ResponseBody
	@RequestMapping("/car/getCars")
	public Map<String, Object> getCars(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		List<CarVO> carVOs = carService.getCars(pageNO,pageSize);
		int count = carService.getCountCar();
		if (carVOs == null || carVOs.isEmpty()) {
			carVOs = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", carVOs);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("/car/addCar")
	public String addCar(HttpServletRequest request) {
		String plateNumber = request.getParameter("plateNumber");
		Integer carStyleId = StringUtils.getInteger(request.getParameter("carStyleId"));
		Integer ownerId = StringUtils.getInteger(request.getParameter("ownerId"));
		String createTime = request.getParameter("createTime");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("plateNumber", plateNumber);
		params.put("carStyleId", carStyleId);
		params.put("owner", ownerId);
		params.put("createTime", createTime);
		carService.addCar(params);
		return "add";
	}
	
	@ResponseBody
	@RequestMapping(value="car/getCar/{id}", method=RequestMethod.GET)
	public CarVO getCar(@PathVariable("id")Integer id, HttpServletRequest request) {
		CarVO carVO = null;
		if (id != null) {
			carVO = carService.getCar(id);
		}
		return carVO;
	}
	
	@ResponseBody
	@RequestMapping("/car/updateCar/{id}")
	public String updateCar(@PathVariable("id")Integer id, HttpServletRequest request) {
		String plateNumber = request.getParameter("plateNumber");
		Integer carStyleId = StringUtils.getInteger(request.getParameter("carStyleId"));
		Integer ownerId = StringUtils.getInteger(request.getParameter("ownerId"));
		String createTime = request.getParameter("createTime");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("plateNumber", plateNumber);
		params.put("carStyleId", carStyleId);
		params.put("owner", ownerId);
		params.put("createTime", createTime);
		carService.updateCar(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping("/car/deleteCar/{id}")
	public String deleteCar(@PathVariable("id")Integer id) {
		carService.deleteCar(id);
		return "delete";
	}
	
	
	
}
