package com.example.tugas1.service;

import com.example.tugas1.model.PendudukModel;

public interface PendudukService {
	PendudukModel selectPenduduk (String nik);
	PendudukModel selectPendudukAja (String nik);
/*	void addPenduduk (PendudukModel penduduk);*/
	void deletePenduduk (String nik);
}
