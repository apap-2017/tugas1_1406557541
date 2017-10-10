package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.One;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;

@Mapper
public interface KeluargaMapper {
	@Select ("SELECT * "
			+ "FROM keluarga "
			+ "WHERE nomor_kk = #{nomor_kk}")
	@Results (value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "kelurahan", column = "id", javaType = KelurahanModel.class, one = @One(select = "selectKelurahan")),
			@Result(property = "anggotaKeluarga", column = "id", javaType = List.class, many = @Many(select = "selectAnggotaKeluarga")),
			})
	KeluargaModel selectKeluarga(@Param("nomor_kk") String nomor_kk);
	
	
	@Select("SELECT * "
			+ "FROM penduduk "
			+"WHERE id_keluarga = #{id}")
	List<PendudukModel> selectAnggotaKeluarga(@Param("id_keluarga") String id_keluarga);
	
	
	@Select("SELECT * "
			+ "FROM kelurahan "
			+"WHERE id = #{id}")
	@Results (value = {
			@Result(property = "kecamatan", column = "id", javaType = KecamatanModel.class, one = @One(select = "selectKecamatan")),
	})
	KelurahanModel selectKelurahan(@Param("id_kelurahan") String id);
	
	
	@Select("SELECT * "
			+ "FROM kecamatan "
			+"WHERE id = #{id}")
	@Results (value = {
			@Result(property = "kota", column = "id", javaType = KotaModel.class, one = @One(select = "selectKota")),
	})
	KecamatanModel selectKecamatan(@Param("id_kecamatan") String id);
	
	
	@Select("SELECT * "
			+ "FROM kota "
			+"WHERE id = #{id}")
	KotaModel selectKota(@Param("id_kota") String id);
}