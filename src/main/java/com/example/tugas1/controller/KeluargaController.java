package com.example.tugas1.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;
import com.example.tugas1.service.KecamatanService;
import com.example.tugas1.service.KeluargaService;
import com.example.tugas1.service.KelurahanService;
import com.example.tugas1.service.KotaService;

@Controller
public class KeluargaController {
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KotaService kotaDAO;

	@RequestMapping("/keluarga")
	public String viewKeluargaSubmit(Model model, @RequestParam(value = "nomor_kk", required = false) String nomor_kk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nomor_kk);
		if (keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			System.out.println(keluarga);
			return "keluarga/keluarga-view";
		} else {
			model.addAttribute("errormessage", "Keluarga dengan NKK " + nomor_kk
					+ " tidak ditemukan, mohon cek kembali Nomor Kartu Keluarga Anda.");
			return "layout/error";
		}
	}

	@RequestMapping("/keluarga/tambah")
	public String formAdd(Model model) {
		
		return "keluarga/keluarga-add";
	}

	/*
	 * Submit & proses form yang sudah dimasukkan sesuai dengan ketentuan
	 */
	@RequestMapping("/keluarga/tambah/submit")
	public String addSubmit(Model model, @RequestParam(value = "alamat", required = false) String alamat,
			@RequestParam(value = "RT", required = false) String RT,
			@RequestParam(value = "RW", required = false) String RW,
			@RequestParam(value = "kelurahan", required = false) int id_kelurahan,
			@RequestParam(value = "kecamatan", required = false) String id_kecamatan,
			@RequestParam(value = "kota", required = false) String id_kota) {
		
//		KecamatanModel kecamatan = kecamatanDAO.selectKecamatanById(id_kecamatan);
//		String kode_kecamatan = kecamatan.getKode_kecamatan();
//		
//		String kkAwal = kode_kecamatan.substring(0, 6);
		
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahanById(id_kelurahan);
		String kode_kelurahan = kelurahan.getKode_kelurahan();
		String kkAwal = kode_kelurahan.substring(0, 6);
		
		//generate date
		Date todayDate = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String kkTengah = dateFormat.format(todayDate);
		
		
		
		// String nikAwal = 6 kode kecamatan
		// String nikTengah = 6 tanggal KK dibuat
//		// String nikAkhir = 4 nomor urut
//		String tanggal = tanggal_lahir.substring(8, 10);
//		String bulan = tanggal_lahir.substring(5, 7);
//		String tahun = tanggal_lahir.substring(2, 4);

		
//		
//		String nikTengah = tanggal + bulan + tahun;
//		
		//Buat nikAkhir
		boolean found = false;
		String nomor_kk = "";
		int i = 0;
		while (!found) {
			i++;
			String kkAkhir = numConvert(i);
			nomor_kk = kkAwal + kkTengah + kkAkhir;
			KeluargaModel keluarga = keluargaDAO.selectKeluargaAja(nomor_kk);
			if (keluarga == null) {
				found = true;
			}
		}
//
		// construct new keluarga
		KeluargaModel keluarga = new KeluargaModel (0, nomor_kk, alamat, RT, RW, id_kelurahan, 0, null,
				null, null, null);
		keluargaDAO.addKeluarga(keluarga);
		model.addAttribute("keluarga", keluarga);
		return "keluarga/success-add";
	}
	
	public static String numConvert(int number){
		String num = "" + number;
		if(num.length() == 1){
			num = "000" + num;
		} else if(num.length() == 2){
			num = "00" + num;
		} else if(num.length() == 3){
			num = "0" + num;
		}
		return num;
	}
}

	