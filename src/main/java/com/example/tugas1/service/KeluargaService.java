package com.example.tugas1.service;

import com.example.tugas1.model.KeluargaModel;

public interface KeluargaService {
	KeluargaModel selectKeluarga (String nomor_kk);
	KeluargaModel selectKeluargaById (int id);
	void addKeluarga (KeluargaModel keluarga);
	KeluargaModel selectKeluargaAja(String nomor_kk);
	void updateTidakBerlaku(String nomor_kk);
}
