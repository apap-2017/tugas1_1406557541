package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KotaModel;

@Mapper
public interface KotaMapper {
	@Select ("SELECT * FROM kota")
	List<KotaModel> selectAllKota();
}
