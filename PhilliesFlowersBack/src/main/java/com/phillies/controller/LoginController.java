package com.phillies.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phillies.domain.Account;
import com.phillies.domain.Order;
import com.phillies.services.AccountService;
import com.phillies.services.OrderService;

@Controller
public class LoginController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private OrderService orderService;

	@GetMapping("/login")
	public String login(HttpSession session) {
		if (session.getAttribute("user")==null)
			return "login";
		return "redirect:/dash";
	}

	@PostMapping("/login")
	public String processLogin(Model model, @RequestParam String name, @RequestParam String pass, HttpSession session) {
		Account account = (Account) getAccount(name, pass);
		if (account==null) {
			model.addAttribute("error", "Error");
			return "login";
		}
		else {
			session.setAttribute("user", account);
			return "redirect:/dash";
		}
	}

	public Account getAccount(String name, String pass) {
		Account temp = accountService.login(name, pass);
		if(temp!=null) {
			
		}
		return temp;
	}
	
	public List<Order> getOrders(String name) {
		return orderService.getUserOrders(name);
	}
}