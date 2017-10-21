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
	
	@RequestMapping("/penduduk/add")
	public String indexAdd() {
		return "penduduk/penduduk-add";
	}
	
	@RequestMapping("/penduduk/tambah")
	public String addSubmit(Model model,
			@RequestParam(value = "nama", required = false) String nama,
			@RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
			@RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
			@RequestParam(value = "jenis_kelamin", required = false) String jenis_kelamin,
			@RequestParam(value = "golongan_darah", required = false) String golongan_darah,
			@RequestParam(value = "agama", required = false) String agama,
			@RequestParam(value = "status_perkawinan", required = false) String status_perkawinan,
			@RequestParam(value = "pekerjaan", required = false) String pekerjaan,
			@RequestParam(value = "kewarganegaraan", required = false) boolean kewarganegaraan,
			@RequestParam(value = "status_kematian", required = false) boolean status_kematian,
			@RequestParam(value = "id_keluarga", required = false) String id_keluarga) {
		KeluargaModel keluargaa= keluargaDAO.selectKeluargaById(id_keluarga);
		String keluarga = keluargaa.getKelurahan().getKecamatan().getKode_kecamatan();
		String nikAwal = keluarga.substring(0,6);
		String tanggal = tanggal_lahir.substring(8, 10);
		String bulan = tanggal_lahir.substring(5, 7);
		String tahun = tanggal_lahir.substring(2, 4);
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
		model.addAttribute("test", nik);
		model.addAttribute("keluarga", keluarga);

		return "penduduk/success-coba";
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
		String id_keluarga = penduduk.getId_keluarga();
		//select keluarga by id_keluarga
		//cek size list di keluargamodel
			//kl 0, ubah status
			//bikin query update keluarga
		model.addAttribute("penduduk", penduduk);
		return "penduduk/penduduk-view";
	}
}
