package com.example.tugas1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tugas1.model.KelurahanModel;

@Service
public class KelurahanServiceDatabase implements KelurahanService {

	@Autowired
	KelurahanMapper kelurahanMapper;
	
	@Override
	public List<KelurahanModel> selectAllKelurahan() {
		// TODO Auto-generated method stub
		kelurahanMapper.selectAllKelurahan();
		return null;
	}

}

