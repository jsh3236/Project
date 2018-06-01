package com.javateam.secureApp1;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.service.AuthJdbcService;
 
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	AuthJdbcService authJdbcService;
	
	// id 유무 점검 : spring security bug patch !
	@RequestMapping("/idCheck")
	public String idCheck(@RequestParam("username") String username,
						   Model model) {
		
		System.out.println(username);
		
		boolean flag = authJdbcService.hasUsername(username);
		System.out.println(flag);
		model.addAttribute("flag", flag);
		
		return "idCheck";
	}

}