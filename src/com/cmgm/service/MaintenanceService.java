package com.cmgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmgm.dao.MaintenanceDao;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:40:30
 *
 */

@Service
public class MaintenanceService {

	@Autowired
	private MaintenanceDao maintenanceDao;
	
}
