package com.example.tugas1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KeluargaMapper;
import com.example.tugas1.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
	private KeluargaMapper keluargaMapper;

	@Override
	public KeluargaModel selectKeluarga(String nomor_kk) {
		log.info ("select keluarga with nomor_kk {}", nomor_kk);
		return keluargaMapper.selectKeluarga(nomor_kk);
	}
	
	@Override
	public KeluargaModel selectKeluargaById(String id) {
		log.info ("select keluarga with nomor_kk {}", id);
		return keluargaMapper.selectKeluargaById(id);
	}

	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		keluargaMapper.addKeluarga(keluarga);
	}
}
