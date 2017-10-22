package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {
	@Select ("SELECT * FROM kelurahan")
	List<KelurahanModel> selectAllKelurahan();

	@Select ("SELECT * FROM kelurahan WHERE id=#{id_kelurahan}")
	KelurahanModel selectKelurahanById(int id_kelurahan);
}
