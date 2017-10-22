package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.service.KecamatanService;

@Mapper
public interface KecamatanMapper {
	@Select ("SELECT * FROM kecamatan")
	List<KecamatanModel> selectAllKecamatan();
	
	@Select ("SELECT * FROM kecamatan where id = #{id_kecamatan}")
	KecamatanModel selectKecamatanById(int id_kecamatan);
}
