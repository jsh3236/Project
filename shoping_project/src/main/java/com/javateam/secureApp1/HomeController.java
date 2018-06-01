package com.javateam.secureApp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.model.vo.Users;
import com.javateam.service.AuthJdbcService;

@Controller
public class HomeController {
	
	@Autowired
	AuthJdbcService authJdbcService;

	@RequestMapping("/welcome")
	public void welcome() {
	}
	
	@RequestMapping("/join")
	public void join() {
	}
	
	@RequestMapping("/joinAction")
	public void join(@RequestParam("username") String username,
					 @RequestParam("password") String password) {
		
		System.out.println("join !");
		
		String hashedPassword = ""; 
		BCryptPasswordEncoder passwordEncoder 
			= new BCryptPasswordEncoder();
		hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
		
		Users users = new Users(username,
								hashedPassword,
								1);
		authJdbcService.insertUsers(users, "ROLE_USER");
	} // 
	
}
