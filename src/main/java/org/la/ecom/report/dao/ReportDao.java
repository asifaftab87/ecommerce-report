package org.la.ecom.report.dao;

import java.util.HashMap;
import java.util.Map;

import org.la.ecom.mysql.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Repository
public class ReportDao {

	@Autowired
	private ResourceLoader resourceLoader; 
	
	public JasperPrint getUserDetailReport(UserDTO userDTO) {
		
		JasperPrint print = null;
		
		try {
			resourceLoader = new DefaultResourceLoader();
			
			String path = resourceLoader.getResource("classpath:reports/user.jrxml").getURI().getPath();
			System.out.println(path);
			
			JasperReport jasperReport = JasperCompileManager.compileReport(path);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("firstName", userDTO.getFirstName());
			parameters.put("lastName", userDTO.getLastName());
			parameters.put("dob", new java.sql.Date(userDTO.getDob().getTime()));
			parameters.put("email", userDTO.getEmail());
			parameters.put("contactNum", userDTO.getContactNum());
			
			print = JasperFillManager.fillReport(jasperReport, parameters);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return print;
	}

}
