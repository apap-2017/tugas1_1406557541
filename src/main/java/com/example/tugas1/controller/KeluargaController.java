package com.example.tugas1.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.example.tugas1.service.PendudukService;

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
		List<KelurahanModel> kelurahan = kelurahanDAO.selectAllKelurahan();
		List<KecamatanModel> kecamatan = kecamatanDAO.selectAllKecamatan();
		List<KotaModel> kota = kotaDAO.selectAllKota();
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kota", kota);
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
		
		
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahanById(id_kelurahan);
		String kode_kelurahan = kelurahan.getKode_kelurahan();
		String kkAwal = kode_kelurahan.substring(0, 6);
		
		//generate date
		Date todayDate = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String kkTengah = dateFormat.format(todayDate);
		
		//Buat kkAkhir
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
	
	/*
	 * Method ubahKeluargauntuk mengubah data keluarga
	 */
	@RequestMapping("/keluarga/ubah/{nomor_kk}")
	public String updatePenduduk(Model model, @PathVariable(value = "nomor_kk") String nomor_kk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nomor_kk);
		List<KelurahanModel> kelurahan = kelurahanDAO.selectAllKelurahan();
		List<KecamatanModel> kecamatan = kecamatanDAO.selectAllKecamatan();
		List<KotaModel> kota = kotaDAO.selectAllKota();
		model.addAttribute("allKelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kota", kota);
		if (keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			return "keluarga/keluarga-update";
		} else {
			model.addAttribute("errormessage", "Keluarga dengan Nomor KK " + nomor_kk
					+ " tidak ditemukan, mohon cek kembali Nomor Kartu Keluarga Anda.");
			return "layout/error";
		}
	}
	
	@RequestMapping(value = "/keluarga/ubah/submit/{nomor_kk}", method = RequestMethod.POST)
    public String ubahKeluarga(Model model, @PathVariable(value = "nomor_kk") String nomor_kk,
    		@ModelAttribute KeluargaModel keluarga,
    		@RequestParam(value = "alamat") String alamat, 
    		@RequestParam(value = "RT") String RT,
			@RequestParam(value = "RW") String RW,
			@RequestParam(value = "kelurahan") int id_kelurahan, 
			@RequestParam(value = "kecamatan") int id_kecamatan,
			@RequestParam(value = "kota") int id_kota)
    {
		KeluargaModel keluargaAwal = keluargaDAO.selectKeluarga(nomor_kk);
		keluarga.setNomor_kk(keluargaAwal.getNomor_kk());
		keluarga.setId(keluargaAwal.getId());
		keluarga.setIs_tidak_berlaku(keluargaAwal.getIs_tidak_berlaku());

		if(keluargaAwal.getId_kelurahan() != keluarga.getId_kelurahan()) {
			KelurahanModel kelurahan = kelurahanDAO.selectKelurahanById(id_kelurahan);
			String kode_kelurahan = kelurahan.getKode_kelurahan();
			String kkAwal = kode_kelurahan.substring(0, 6);
			
			//generate date
			Date todayDate = new Date();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			String kkTengah = dateFormat.format(todayDate);
			
			//Buat kkAkhir
			boolean found = false;
			int i = 0;
			while (!found) {
				i++;
				String kkAkhir = numConvert(i);
				nomor_kk = kkAwal + kkTengah + kkAkhir;
				keluarga = keluargaDAO.selectKeluargaAja(nomor_kk);
				if (keluarga == null) {
					found = true;
				}
			}
		}
		
		keluargaDAO.updateKeluarga(keluarga);
		KeluargaModel keluargaBaru = new KeluargaModel (keluargaAwal.getId(), nomor_kk, alamat, RT, RW, id_kelurahan, 0, null,
				null, null, null);
		model.addAttribute("keluarga", keluargaBaru);
		model.addAttribute("nomor_kk", nomor_kk);
        return "keluarga/success-update";
    }
}

	