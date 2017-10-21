package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KelurahanModel;

public interface KelurahanMapper {
	@Select ("SELECT * FROM kelurahan")
	List<KelurahanModel> selectAllKelurahan();
}
