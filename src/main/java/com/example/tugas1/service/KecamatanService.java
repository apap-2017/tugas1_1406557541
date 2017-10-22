package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.KecamatanModel;

public interface KecamatanService {
	List<KecamatanModel> selectAllKecamatan();
	KecamatanModel selectKecamatanById(int id_kecamatan);
	List<KecamatanModel> selectKecamatanByKota(String id_kota);
}
