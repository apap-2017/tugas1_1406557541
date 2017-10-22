package com.example.tugas1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class PendudukController {
	@Autowired
	PendudukService pendudukDAO;
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	KelurahanService kelurahanDAO;

	@Autowired
	KecamatanService kecamatanDAO;

	@Autowired
	KotaService kotaDAO;

	@RequestMapping("/master")
	public String master() {
		return "layout/master";
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/penduduk")
	public String viewPendudukSubmit(Model model, @RequestParam(value = "nik", required = true) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk != null) {
			model.addAttribute("penduduk", penduduk);
			System.out.println(penduduk);
			return "penduduk/penduduk-view";
		} else {
			model.addAttribute("errormessage", "Penduduk dengan NIK " + nik
					+ " tidak ditemukan, mohon cek kembali Nomor Induk Kependudukan Anda.");
			return "layout/error";
		}
	}

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
	public String addSubmit(Model model, @RequestParam(value = "nama", required = false) String nama,
			@RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
			@RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
			@RequestParam(value = "jenis_kelamin", required = false) int jenis_kelamin,
			@RequestParam(value = "golongan_darah", required = false) String golongan_darah,
			@RequestParam(value = "agama", required = false) String agama,
			@RequestParam(value = "status_perkawinan", required = false) String status_perkawinan,
			@RequestParam(value = "status_dalam_keluarga", required = false) String status_dalam_keluarga,
			@RequestParam(value = "pekerjaan", required = false) String pekerjaan,
			@RequestParam(value = "is_wni", required = false) int is_wni,
			@RequestParam(value = "is_wafat", required = false) int is_wafat,
			@RequestParam(value = "id_keluarga", required = false) int id_keluarga) {
		KeluargaModel keluargaa = keluargaDAO.selectKeluargaById(id_keluarga);

		String keluarga = keluargaa.getKelurahan().getKecamatan().getKode_kecamatan();
		String nikAwal = keluarga.substring(0, 6);
		String tanggal = tanggal_lahir.substring(8, 10);
		String bulan = tanggal_lahir.substring(5, 7);
		String tahun = tanggal_lahir.substring(2, 4);

		// kalo cewe
		int tanggal2 = Integer.parseInt(tanggal);
		if (jenis_kelamin == 1) {
			tanggal2 += 40;
		}
		tanggal = "" + tanggal2;

		String nikTengah = tanggal + bulan + tahun;
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
		model.addAttribute("nikBaru", nik);
		model.addAttribute("penduduk", penduduk);
		return "penduduk/success-add";
	}

	public static String numConvert(int number) {
		String num = "" + number;
		if (num.length() == 1) {
			num = "000" + num;
		} else if (num.length() == 2) {
			num = "00" + num;
		} else if (num.length() == 3) {
			num = "0" + num;
		}
		return num;
	}

	/*
	 * Method updatePenduduk untuk mengubah data penduduk
	 */
	@RequestMapping("/penduduk/ubah/{nik}")
	public String updatePenduduk(Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk != null) {
			model.addAttribute("penduduk", penduduk);
			return "penduduk/penduduk-update";
		} else {
			model.addAttribute("errormessage", "Penduduk dengan NIK " + nik
					+ " tidak ditemukan, mohon cek kembali Nomor Induk Kependudukan Anda.");
			return "layout/error";
		}
	}

	@RequestMapping(value = "/penduduk/ubah/submit/{nik}", method = RequestMethod.POST)
	public String updatePendudukSubmit(Model model, @PathVariable(value = "nik") String nik, PendudukModel penduduk,
			@RequestParam(value = "nama") String nama, @RequestParam(value = "tempat_lahir") String tempat_lahir,
			@RequestParam(value = "tanggal_lahir") String tanggal_lahir,
			@RequestParam(value = "golongan_darah") String golongan_darah, @RequestParam(value = "agama") String agama,
			@RequestParam(value = "status_perkawinan") String status_perkawinan,
			@RequestParam(value = "pekerjaan") String pekerjaan, @RequestParam(value = "is_wni") int is_wni,
			@RequestParam(value = "is_wafat") int is_wafat, @RequestParam(value = "id_keluarga") int id_keluarga,
			@RequestParam(value = "status_dalam_keluarga") String status_dalam_keluarga) {
		PendudukModel pendudukAwal = pendudukDAO.selectPenduduk(nik);

		penduduk.setId(pendudukAwal.getId());
		penduduk.setJenis_kelamin(pendudukAwal.getJenis_kelamin());
		model.addAttribute("nik", penduduk.getNik());
		String nikAwal = "";
		String nikTengah = "";
		String nikAkhir = "";
		if (!pendudukAwal.getTanggal_lahir().equals(penduduk.getTanggal_lahir())
				|| pendudukAwal.getId_keluarga() != (penduduk.getId_keluarga())) {
			KeluargaModel keluargaa = keluargaDAO.selectKeluargaById(penduduk.getId_keluarga());
			String keluarga = keluargaa.getKelurahan().getKecamatan().getKode_kecamatan();
			nikAwal = keluarga.substring(0, 6);
			String tanggal = penduduk.getTanggal_lahir().substring(8, 10);
			String bulan = penduduk.getTanggal_lahir().substring(5, 7);
			String tahun = penduduk.getTanggal_lahir().substring(2, 4);

			// kalo cewe
			int tanggal2 = Integer.parseInt(tanggal);
			if (pendudukAwal.getJenis_kelamin() == 1) {
				tanggal2 += 40;
			}
			tanggal = "" + tanggal2;

			nikTengah = tanggal + bulan + tahun;

			boolean found = false;
			int i = 0;
			while (!found) {
				i++;
				nikAkhir = numConvert(i);
				nik = nikAwal + nikTengah + nikAkhir;
				penduduk = pendudukDAO.selectPendudukAja(nik);
				if (penduduk == null) {
					found = true;
				}
			}
		}
		String nikFinal = nikAwal + nikTengah + nikAkhir;
		model.addAttribute("nik", nikFinal);
		PendudukModel pendudukBaru = new PendudukModel(pendudukAwal.getId(), nikFinal, nama, tempat_lahir,
				tanggal_lahir, pendudukAwal.getJenis_kelamin(), is_wni, id_keluarga, agama, pekerjaan,
				status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat, null, null, null, null);
		model.addAttribute("penduduk", pendudukBaru);
		pendudukDAO.updatePenduduk(pendudukBaru);
		return "penduduk/success-update";
	}

	/*
	 * Method deletePendudukSubmit untuk mengubah status wafat penduduk,
	 * kemudian cek anggota keluarga apabila seluruh anggota keluarga wafat,
	 * ubah status keluarga menjadi tidak berlaku
	 */
	@RequestMapping("/penduduk/mati/{nik}")
	public String deletePendudukSubmit(Model model, @PathVariable(value = "nik", required = true) String nik) {
		pendudukDAO.deletePenduduk(nik);
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		int id_keluarga = penduduk.getId_keluarga();
		KeluargaModel keluarga = keluargaDAO.selectKeluargaById(id_keluarga);
		List<PendudukModel> anggotaKel = keluarga.getAnggotaKeluarga();

		if (anggotaKel.size() == 0) {
			keluargaDAO.updateTidakBerlaku(id_keluarga);
		}
		/*
		 * PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		 * 
		 * int id_keluarga = penduduk.getId_keluarga(); KeluargaModel keluarga =
		 * keluargaDAO.selectKeluargaById(id_keluarga);
		 * 
		 * List<PendudukModel> anggotaKel = keluarga.getAnggotaKeluarga(); int
		 * countWafat = 0; for (int i = 0; i < anggotaKel.size(); i++) { if
		 * (anggotaKel.get(i).getIs_wafat() == 1) { countWafat++; } }
		 * 
		 * if (countWafat == anggotaKel.size()) { String nomor_kk =
		 * keluarga.getNomor_kk(); keluargaDAO.updateTidakBerlaku(nomor_kk); }
		 * model.addAttribute("cekBerlaku", keluarga.getIs_tidak_berlaku());
		 * model.addAttribute("nikLama", nik); model.addAttribute("nikBaru",
		 * penduduk.getNik()); model.addAttribute("penduduk", penduduk);
		 */
		return "penduduk/success-wafat";
	}

	@RequestMapping("/penduduk/cari")
	public String cariPenduduk(Model model, @RequestParam(value = "id_kota", required = false) String id_kota,
			@RequestParam(value = "id_kecamatan", required = false) String id_kecamatan,
			@RequestParam(value = "id_kelurahan", required = false) String id_kelurahan) {
		List<KotaModel> daftarKota = kotaDAO.selectAllKota();
		List<KecamatanModel> daftarKecamatan = kecamatanDAO.selectKecamatanByKota(id_kota);
		List<KelurahanModel> daftarKelurahan = kelurahanDAO.selectKelurahanByKecamatan(id_kecamatan);

		model.addAttribute("daftarKota", daftarKota);
		model.addAttribute("id_kota", id_kota);
		model.addAttribute("daftarKecamatan", daftarKecamatan);
		model.addAttribute("id_kecamatan", id_kecamatan);
		model.addAttribute("daftarKelurahan", daftarKelurahan);
		model.addAttribute("id_kelurahan", id_kelurahan);
		
		if (id_kota != null && id_kecamatan != null && id_kelurahan != null) {
			List<PendudukModel> daftarPenduduk = pendudukDAO.selectPendudukByIdKelurahan(id_kelurahan);
			model.addAttribute("penduduk", daftarPenduduk);

			PendudukModel termuda = pendudukDAO.pendudukTermuda(id_kelurahan);
			model.addAttribute("termuda", termuda);

			PendudukModel tertua = pendudukDAO.pendudukTertua(id_kelurahan);
			model.addAttribute("tertua", tertua);
		}
		return "penduduk/penduduk-cari";
	}

}