package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KotaMapper;
import com.example.tugas1.model.KotaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaServiceDB implements KotaService {
	
	@Autowired
	KotaMapper kotaMapper;
	
	@Override
	public List<KotaModel> selectAllKota() {
		// TODO Auto-generated method stub
		return kotaMapper.selectAllKota();
		
	}

}
