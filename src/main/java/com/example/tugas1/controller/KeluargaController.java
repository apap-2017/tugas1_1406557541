package com.example.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.service.KeluargaService;

@Controller
public class KeluargaController {
	@Autowired
	KeluargaService keluargaDAO;
	
	@RequestMapping("/keluarga")
	public String indexSubmit(Model model, @RequestParam(value="nomor_kk", required=true) String nomor_kk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nomor_kk);
		model.addAttribute("keluarga", keluarga);
		return "keluarga/keluarga-view";
	}
	
	
}
