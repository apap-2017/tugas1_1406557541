package com.example.tugas1.controller;

import java.util.List;

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
	
	@RequestMapping("/penduduk")
	public String viewPendudukSubmit(Model model, @RequestParam(value="nik", required=true) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk != null) {
			model.addAttribute("penduduk", penduduk);
			System.out.println(penduduk);
			return "penduduk/penduduk-view";
		} else {
			model.addAttribute("errormessage", "Penduduk dengan NIK " + nik + " tidak ditemukan, mohon cek kembali Nomor Induk Kependudukan Anda.");
			return "layout/error";
		}
	}
	
/*	@RequestMapping("/penduduk")
	public String viewPendudukSubmit(Model model, @RequestParam(value="nik", required=true) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
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
	}*/
	
	
	/*
	 * Menampilkan form untuk menginput data penduduk
	 */
	@RequestMapping("/penduduk/tambah")
	public String formAdd() {
		return "penduduk/penduduk-add";
	}
	
	
	/*
	 * Submit & proses form yang sudah dimasukkan sesuai dengan ketentuan
	 */
	@RequestMapping("/penduduk/tambah/submit")
	public String addSubmit(Model model,
			@RequestParam(value = "nama", required = false) String nama,
			@RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
			@RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
			@RequestParam(value = "jenis_kelamin", required = false) String jenis_kelamin,
			@RequestParam(value = "golongan_darah", required = false) String golongan_darah,
			@RequestParam(value = "agama", required = false) String agama,
			@RequestParam(value = "status_perkawinan", required = false) String status_perkawinan,
			@RequestParam(value = "status_dalam_keluarga", required = false) String status_dalam_keluarga,
			@RequestParam(value = "pekerjaan", required = false) String pekerjaan,
			@RequestParam(value = "is_wni", required = false) int is_wni,
			@RequestParam(value = "is_wafat", required = false) int is_wafat,
			@RequestParam(value = "id_keluarga", required = false) int id_keluarga) {
		KeluargaModel keluargaa= keluargaDAO.selectKeluargaById(id_keluarga);
		
		String keluarga = keluargaa.getKelurahan().getKecamatan().getKode_kecamatan();
		String nikAwal = keluarga.substring(0,6);
		String tanggal = tanggal_lahir.substring(8, 10);
		String bulan = tanggal_lahir.substring(5, 7);
		String tahun = tanggal_lahir.substring(2, 4);
		
		//kalo cewe
		int tanggal2 = Integer.parseInt(tanggal);
			if(jenis_kelamin.equalsIgnoreCase("1")){
				tanggal2 += 40;
			}
		tanggal = "" + tanggal2;
		
		String nikTengah = tanggal+bulan+tahun;
		boolean found = false;
		String nik = "";
		int i = 0;
		while (!found){
			i++;
			String nikAkhir = numConvert(i);
			nik = nikAwal + nikTengah + nikAkhir;
			PendudukModel penduduk = pendudukDAO.selectPendudukAja(nik);
			if (penduduk == null){
				found = true;
			}
		}
		
		//construct new penduduk
		PendudukModel penduduk = new PendudukModel (0, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat, null, null, null, null);
		pendudukDAO.addPenduduk(penduduk);
		model.addAttribute("penduduk", penduduk);
		return "penduduk/success-add";
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

	@RequestMapping("/penduduk/delete/{nik}")
	public String deletePendudukSubmit(Model model, @PathVariable(value="nik", required=true) String nik) {
		pendudukDAO.deletePenduduk(nik);
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		int id_keluarga = penduduk.getId_keluarga();
		KeluargaModel keluarga = keluargaDAO.selectKeluargaById(id_keluarga);
		List<PendudukModel> anggotaKel = keluarga.getAnggotaKeluarga();
		int count = 0;
		for (int i = 0; i < anggotaKel.size(); i++) {
			if (anggotaKel.get(i).getIs_wafat() == 1) {
				count++;
			}
		}
		if (count == anggotaKel.size()) {
			String nomor_kk = keluarga.getNomor_kk();
			keluargaDAO.updateTidakBerlaku(nomor_kk);
		}
		model.addAttribute("penduduk", penduduk);
		return "penduduk/penduduk-view";
	}
}