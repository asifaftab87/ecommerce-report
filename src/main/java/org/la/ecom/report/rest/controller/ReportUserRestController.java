package org.la.ecom.report.rest.controller;

import org.la.ecom.mysql.api.dto.UserDTO;
import org.la.ecom.report.model.Report;
import org.la.ecom.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/user")
public class ReportUserRestController {

	@Autowired
	private ReportService reportService;
	
	@PostMapping("/detail")
	public Report getUserDetailReport(@RequestBody UserDTO userDTO) throws JRException {
		
		Report userDetailReport = reportService.getUserDetailReport(userDTO);
		
		return userDetailReport;
		
	}
	
	@GetMapping("/test")
	public UserDTO get() {
		UserDTO u = new UserDTO();
		u.setDob(new java.util.Date());
		return u;
	}
}
