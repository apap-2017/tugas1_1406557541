package com.example.tugas1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KeluargaModel;

@Mapper
public interface KeluargaMapper {
	@Select("SELECT *"
			+ "FROM keluarga "
			+ "WHERE nomor_kk = #{nomor_kk}")
	KeluargaModel selectKeluarga (@Param("nomor_kk") String nomor_kk);
}
