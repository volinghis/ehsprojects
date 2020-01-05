package com.ehs.ehsui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

	@RequestMapping(value = "/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "/index.html";
	}
}
