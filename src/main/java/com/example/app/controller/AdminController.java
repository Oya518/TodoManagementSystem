package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Admin;
import com.example.app.domain.Login;
import com.example.app.login.LoginStatus;
import com.example.app.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute(new Login());
		return "login.html";
	}
	
	@PostMapping("/login")
	public String login(
			HttpSession session,
			@Valid Login login,
			Errors errors
			) throws Exception{
		
		if(errors.hasErrors()) {
			return "login";
		}
		
		Admin admin = adminService.getAdminByLoginId(login.getLoginId());
		if(admin == null || !login.isCorrectPassword(admin.getLoginPass())) {
			errors.rejectValue("loginId", "error.incorrect_id_or_password");
			return "login";
		}
		
		LoginStatus loginStatus = new LoginStatus(admin.getId(),
				admin.getLoginId());
		
		session.setAttribute("loginStatus", loginStatus);
		
		return "redirect:/listTodo";
	}
	
	@GetMapping("/logout")
	public String logout(
			HttpSession session,
			RedirectAttributes redirectAttributes
			) {
		session.removeAttribute("loginStatus");
		redirectAttributes.addFlashAttribute("message","ログアウトしました");
		
		return "redirect:/login";
	}

}
