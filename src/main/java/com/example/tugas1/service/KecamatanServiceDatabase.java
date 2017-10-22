package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KecamatanMapper;
import com.example.tugas1.model.KecamatanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService {

	@Autowired
	KecamatanMapper kecamatanMapper;
	
	@Override
	public List<KecamatanModel> selectAllKecamatan() {
		// TODO Auto-generated method stub
		return kecamatanMapper.selectAllKecamatan();
	}

	@Override
	public KecamatanModel selectKecamatanById(int id_kecamatan) {
		// TODO Auto-generated method stub
		log.info("Select kecamatan with id {}", id_kecamatan);
		return kecamatanMapper.selectKecamatanById(id_kecamatan);
	}
	

}
