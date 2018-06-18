package com.javateam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javateam.service.deprecated.JpaDAO;

@Controller
public class BoardController {
	
	@Autowired 
	private JpaDAO dao;
	
	@RequestMapping("/mouseBoard")
	public String home() {
		
		return "mouseBoard";
	}
}
