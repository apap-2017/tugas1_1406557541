package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.PendudukModel;

public interface PendudukService {
	PendudukModel selectPenduduk (String nik);
	PendudukModel selectPendudukAja (String nik);
	void addPenduduk (PendudukModel penduduk);
	void deletePenduduk (String nik);
	void updatePenduduk (PendudukModel penduduk);
	List<PendudukModel> selectPendudukByIdKelurahan(String id_kelurahan);
	PendudukModel pendudukTermuda(String id_kelurahan);
	PendudukModel pendudukTertua(String id_kelurahan);
}
