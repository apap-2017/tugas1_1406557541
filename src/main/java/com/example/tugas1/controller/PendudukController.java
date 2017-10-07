package com.example.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.PendudukModel;
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
	
	@RequestMapping("/penduduk")
	public String indexSubmit(Model model, @RequestParam(value="nik", required=true) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		model.addAttribute("penduduk", penduduk);
		return "penduduk/penduduk-view";
	}
}
