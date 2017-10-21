package com.example.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.PendudukModel;
import com.example.tugas1.service.KeluargaService;

@Controller
public class KeluargaController {
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;

	@RequestMapping("/keluarga")
	public String viewKeluargaSubmit(Model model, @RequestParam(value = "nomor_kk", required = true) String nomor_kk) {
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
	public String formAdd() {
		KelurahanModel kelurahan = 
		return "keluarga/keluarga-add";
	}

	/*
	 * Submit & proses form yang sudah dimasukkan sesuai dengan ketentuan
	 */
	@RequestMapping("/keluarga/tambah/submit")
	public String addSubmit(Model model, @RequestParam(value = "alamat", required = false) String alamat,
			@RequestParam(value = "RT", required = false) String RT,
			@RequestParam(value = "RW", required = false) String RW,
			@RequestParam(value = "id_kelurahan", required = false) String id_kelurahan,
			@RequestParam(value = "is_tidak_berlaku", required = false) boolean is_tidak_berlaku) {

		// String nikAwal = 6 kode kecamatan
		// String nikTengah = 6 tanggal KK dibuat
		// String nikAkhir = 4 nomor urut
		String tanggal = tanggal_lahir.substring(8, 10);
		String bulan = tanggal_lahir.substring(5, 7);
		String tahun = tanggal_lahir.substring(2, 4);

		
		
		String nikTengah = tanggal + bulan + tahun;
		
		//Buat nikAkhir
		boolean found = false;
		String nik = "";
		int i = 0;
		while (!found) {
			i++;
			String nikAkhir = numConvert(i);
			nik = nikAwal + nikTengah + nikAkhir;
			PendudukModel penduduk = pendudukDAO.selectPendudukAja(nik);
			if (penduduk == null) {
				found = true;
			}
		}

		// construct new penduduk
		PendudukModel penduduk = new PendudukModel(0, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni,
				id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat, null,
				null, null, null);
		pendudukDAO.addPenduduk(penduduk);
		model.addAttribute("penduduk", penduduk);
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