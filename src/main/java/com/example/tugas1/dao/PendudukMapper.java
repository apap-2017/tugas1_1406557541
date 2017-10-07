package com.example.tugas1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	
	@Select("SELECT *"
			+ "FROM penduduk "
			+ "WHERE nik = #{nik}")
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	
}
