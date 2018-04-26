package com.cmgm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cmgm.VO.CarVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Car;
import com.cmgm.entity.CarStyle;
import com.cmgm.entity.Owner;
import com.cmgm.entity.User;
import com.cmgm.service.CarService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Bio
 * @date   2018年4月8日
 * @time   上午1:01:29
 * 车辆管理
 */

@SessionAttributes(value={"user"})
@Controller
public class CarController {

	@Autowired
	private CarService carService;
	
	@RequestMapping("/car/carManage")
	public String carManage() {
		return "carManage";
	}
	
	@RequestMapping("/car/carInfo")
	public String carInfo() {
		return "carInfo";
	}
	
	//获取车主下拉列表
	@ResponseBody
	@RequestMapping("/car/ownerList")
	public List<Owner> getOwnerList(Model model) throws JsonProcessingException {
		List<Owner> ownerList = carService.getOwnerList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(ownerList);
		model.addAttribute("ownerList", json);
		return ownerList;
	}
	
	//获取车型下拉列表
	@ResponseBody
	@RequestMapping("/car/carStyleList")
	public List<CarStyle> getCarStyleList(Model model) throws JsonProcessingException {
		List<CarStyle> carStyleList = carService.getCarStyleList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(carStyleList);
		model.addAttribute("carStyleList", json);
		return carStyleList;
	}
	
	//获取车牌下拉列表
	@ResponseBody
	@RequestMapping("/car/plateNumberList")
	public List<Car> getPlateNumberList(Model model,HttpSession httpSession) throws JsonProcessingException {
		User user = (User) httpSession.getAttribute("user");
		Integer ownerId = (user.getOwner().getId())==null?null:(user.getOwner().getId());
		List<Car> plateNumberList = carService.getPlateNumberList(ownerId);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(plateNumberList);
		model.addAttribute("plateNumberList", json);
		return plateNumberList;
	}
	
	@ResponseBody
	@RequestMapping("/car/getCars")
	public Map<String, Object> getCars(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		Integer ownerId = StringUtils.getInteger(request.getParameter("ownerId"));
		String plateNumber = request.getParameter("plateNumber");
		Integer carStyleId = StringUtils.getInteger(request.getParameter("carStyleId"));
		List<CarVO> carVOs = carService.getCars(pageNO,pageSize,ownerId,plateNumber,carStyleId);
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
		params.put("ownerId", ownerId);
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
	
	//车辆信息显示
	@ResponseBody
	@RequestMapping("/car/showCarInfo/{carId}")
	public CarVO getCarVOByPlateNumber(@PathVariable("carId")Integer carId,HttpSession httpSession) {
		CarVO carVO = null;
		User user = (User) httpSession.getAttribute("user");
		Integer ownerId = (user.getOwner().getId())==null?null:(user.getOwner().getId());
		if (ownerId != null) {
			carVO = carService.getCar(StringUtils.getInteger(carId));
		}
		return carVO;
	}
	
	//车主信息显示
	@ResponseBody
	@RequestMapping("/car/showOwnerInfo/{ownerId}")
	public CarVO getOwnerByOnwerId(@PathVariable("ownerId")Integer ownerId) {
		CarVO carVO  = null;
		if (ownerId != null) {
			carVO = carService.getOwnerVOByOwnerId(ownerId);
		}
		return carVO;
	}
	
}
