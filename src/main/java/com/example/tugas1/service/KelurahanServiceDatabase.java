package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KelurahanMapper;
import com.example.tugas1.model.KelurahanModel;

@Service
public class KelurahanServiceDatabase implements KelurahanService {

	@Autowired
	KelurahanMapper kelurahanMapper;
	
	@Override
	public List<KelurahanModel> selectAllKelurahan() {
		// TODO Auto-generated method stub
		kelurahanMapper.selectAllKelurahan();
		return kelurahanMapper.selectAllKelurahan();
	}

	@Override
	public KelurahanModel selectKelurahanById(int id_kelurahan) {
		// TODO Auto-generated method stub
		return kelurahanMapper.selectKelurahanById(id_kelurahan);
	}

	@Override
	public List<KelurahanModel> selectKelurahanByKecamatan(String id_kecamatan) {
		// TODO Auto-generated method stub
		return kelurahanMapper.selectKelurahanByKecamatan(id_kecamatan);
	}

}

