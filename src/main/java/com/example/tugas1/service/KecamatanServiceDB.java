package com.example.tugas1.service;

import org.apache.ibatis.annotations.Mapper;
import com.example.tugas1.model.KecamatanModel;
@Mapper
public interface KecamatanServiceDB {
	List<KecamatanModel> selectAllKecamatan();
}
