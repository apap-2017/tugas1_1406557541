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
	@Select ("SELECT id, nomor_kk, id_kelurahan, alamat, RT, RW "
			+ "FROM keluarga "
			+ "WHERE nomor_kk = #{nomor_kk}")
	@Results (value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "id_kelurahan", column = "id_kelurahan"),
			@Result(property = "alamat", column = "alamat"),
			@Result(property = "RT", column = "RT"),
			@Result(property = "RW", column = "RW"),
			@Result(property = "kelurahan", column = "id", javaType = KelurahanModel.class, one = @One(select = "selectKelurahan")),
			@Result(property = "anggotaKeluarga", column = "id", javaType = List.class, many = @Many(select = "selectAnggotaKeluarga")),
			})
	KeluargaModel selectKeluarga(@Param("nomor_kk") String nomor_kk);
	
	
	@Select ("SELECT id, nomor_kk, id_kelurahan, alamat, RT, RW "
			+ "FROM keluarga "
			+ "WHERE id = #{id}")
	@Results (value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "id_kelurahan", column = "id_kelurahan"),
			@Result(property = "alamat", column = "alamat"),
			@Result(property = "RT", column = "RT"),
			@Result(property = "RW", column = "RW"),
			})
	KeluargaModel selectKeluargaById(@Param("id") String id);
	
	
	/*@Select("SELECT id, nik, nama, tempat_lahir, tanggal_lahir, agama, status_perkawinan, pekerjaan, status_dalam_keluarga, is_wni, id_keluarga, jenis_kelamin "
			+ "FROM penduduk "
			+"WHERE id_keluarga = #{id}")
	@Results (value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nik", column = "nik"),
			@Result(property = "nama", column = "nama"),
			@Result(property = "tempat_lahir", column = "tempat_lahir"),
			@Result(property = "tanggal_lahir", column = "tanggal_lahir"),
			@Result(property = "agama", column = "agama"),
			@Result(property = "status_perkawinan", column = "status_perkawinan"),
			@Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "status_dalam_keluarga", column = "status_dalam_keluarga"),
			@Result(property = "is_wni", column = "is_wni"),
			@Result(property = "id_keluarga", column = "id_keluarga"),
			@Result(property = "jenis_kelamin", column = "jenis_kelamin"),
			})
	List<PendudukModel> selectAnggotaKeluarga(@Param("id_keluarga") String id_keluarga);
	
	
	@Select("SELECT id, id_kecamatan, nama_kelurahan "
			+ "FROM kelurahan "
			+"WHERE id = #{id_kelurahan}")
	@Results (value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nama_kelurahan", column = "nama_kelurahan"),
			@Result(property = "kecamatan", column = "id", javaType = KecamatanModel.class, one = @One(select = "selectKecamatan")),
	})
	KelurahanModel selectKelurahan(@Param("id_kelurahan") String id);
	
	
	@Select("SELECT id, id_kota, nama_kecamatan "
			+ "FROM kecamatan "
			+"WHERE id = #{id_kecamatan}")
	@Results (value = {
			
			@Result(property = "kota", column = "id", javaType = KotaModel.class, one = @One(select = "selectKota")),
	})
	KecamatanModel selectKecamatan(@Param("id_kecamatan") String id);
	
	
	@Select("SELECT id, nama_kota "
			+ "FROM kota "
			+"WHERE id = #{id_kota}")
	KotaModel selectKota(@Param("id_kota") String id);*/
}