package com.example.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.PendudukModel;
import com.example.tugas1.service.KeluargaService;
import com.example.tugas1.service.PendudukService;

@Controller
public class PendudukController {
	@Autowired
	PendudukService pendudukDAO;
	@Autowired
	KeluargaService keluargaDAO;
	
	@RequestMapping("/master")
	public String master() {
		return "layout/master";
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
//	@RequestMapping("/penduduk")
//	public String viewPendudukSubmit(Model model, @RequestParam(value="nik", required=true) String nik) {
//		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
//		if (penduduk != null) {
//			model.addAttribute("penduduk", penduduk);
//			System.out.println(penduduk);
//			return "penduduk/penduduk-view";
//		} else {
//			model.addAttribute("errormessage", "Penduduk dengan NIK " + nik + " tidak ditemukan, mohon cek kembali Nomor Induk Kependudukan Anda.");
//			return "layout/error";
//		}
//	}
	
	@RequestMapping("/penduduk")
	public String viewPendudukSubmit(Model model, @RequestParam(value="nik", required=true) String nik) {
//		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		PendudukModel penduduk = pendudukDAO.selectPendudukAja(nik);
		if (penduduk != null) {
			KeluargaModel keluarga = keluargaDAO.selectKeluargaById(penduduk.getId_keluarga());
			model.addAttribute("penduduk", penduduk);
			model.addAttribute("keluarga", keluarga);
			System.out.println(penduduk);
			return "penduduk/penduduk-view";
		} else {
			model.addAttribute("errormessage", "Penduduk dengan NIK " + nik + " tidak ditemukan, mohon cek kembali Nomor Induk Kependudukan Anda.");
			return "layout/error";
		}
	}
	
	@RequestMapping("/penduduk/add")
	public String indexAdd() {
		return "penduduk/penduduk-add";
	}

	@RequestMapping("/penduduk/delete/{nik}")
	public String deletePendudukSubmit(Model model, @PathVariable(value="nik", required=true) String nik) {
		pendudukDAO.deletePenduduk(nik);
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		String id_keluarga = penduduk.getId_keluarga();
		//select keluarga by id_keluarga
		//cek size list di keluargamodel
			//kl 0, ubah status
			//bikin query update keluarga
		model.addAttribute("penduduk", penduduk);
		return "penduduk/penduduk-view";
	}
}
