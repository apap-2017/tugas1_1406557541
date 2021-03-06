package com.example.tugas1.service;
import java.util.List;

import com.example.tugas1.model.KelurahanModel;

public interface KelurahanService {
	List<KelurahanModel> selectAllKelurahan();

	KelurahanModel selectKelurahanById(int id_kelurahan);

	List<KelurahanModel> selectKelurahanByKecamatan(String id_kecamatan);
}
