package com.project.controller.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

	@Qualifier("localConnection")
	@Autowired
	Connection connection;

	@GetMapping(value = "/testds")
	public String test1() {

		String val ="";
		

		try {
			if(connection!=null && !connection.isClosed()) {
				val = " conn var";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return val;
	}

}
