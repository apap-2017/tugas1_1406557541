package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.PendudukMapper;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	
	@Autowired
	private PendudukMapper pendudukMapper;
	
	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info ("select penduduk with nik {}", nik);
		return pendudukMapper.selectPenduduk(nik);
	}
	
	@Override
	public PendudukModel selectPendudukAja(String nik) {
		log.info ("select penduduk with nik {}", nik);
		return pendudukMapper.selectPendudukAja(nik);
	}
	
	/*
	 * Mengubah penduduk menjadi wafat
	 */
	@Override
	public void deletePenduduk(String nik) {
		log.info ("DELETE penduduk with nik {}", nik);
		pendudukMapper.deletePenduduk(nik);
	}

	@Override
	public void addPenduduk(PendudukModel penduduk) {
		pendudukMapper.addPenduduk(penduduk);
	}

	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		pendudukMapper.updatePenduduk(penduduk);
	}

	@Override
	public List<PendudukModel> selectPendudukByIdKelurahan(String id_kelurahan) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectPendudukByIdKelurahan(id_kelurahan);
	}

	@Override
	public PendudukModel pendudukTermuda(String id_kelurahan) {
		// TODO Auto-generated method stub
		return pendudukMapper.pendudukTermuda(id_kelurahan);
	}

	@Override
	public PendudukModel pendudukTertua(String id_kelurahan) {
		// TODO Auto-generated method stub
		return pendudukMapper.pendudukTertua(id_kelurahan);
	}
}
