package com.example.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tugas1.service.PendudukService;

@Controller
public class PendudukController {
	@Autowired
	PendudukService pendudukDAO;
	
	@RequestMapping("/master")
	public String master() {
		return "layout/master";
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
