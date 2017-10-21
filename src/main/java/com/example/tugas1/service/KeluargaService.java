package com.example.tugas1.service;

import com.example.tugas1.model.KeluargaModel;

public interface KeluargaService {
	KeluargaModel selectKeluarga (String nomor_kk);
	KeluargaModel selectKeluargaById (String id);
	void addKeluarga (KeluargaModel keluarga);
}
