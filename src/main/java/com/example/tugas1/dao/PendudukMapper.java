package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	@Select ("SELECT id, nik, nama, tempat_lahir, tanggal_lahir, id_keluarga, golongan_darah, agama, status_perkawinan, pekerjaan, is_wni, is_wafat "
			+ "FROM penduduk "
			+ "WHERE nik = #{nik}")
	@Results(value = { @Result(property = "nik", column = "nik"),
			@Result(property = "id", column = "id"),
			@Result(property = "nik", column = "nik"),
			@Result(property = "nama", column = "nama"),
			@Result(property = "tempat_lahir", column = "tempat_lahir"),
			@Result(property = "tanggal_lahir", column = "tanggal_lahir"),
			@Result(property = "id_keluarga", column = "id_keluarga"),
			@Result(property = "golongan_darah", column = "golongan_darah"),
			@Result(property = "agama", column = "agama"),
			@Result(property = "status_perkawinan", column = "status_perkawinan"),
			@Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "is_wni", column = "is_wni"),
			@Result(property = "is_wafat", column = "is_wafat"),
			@Result(property = "keluarga", column = "id", javaType = KeluargaModel.class, one = @One(select = "selectKeluarga")) })
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Select("SELECT keluarga.id, keluarga.nomor_kk, keluarga.alamat, keluarga.RT, keluarga.RW, keluarga.id_kelurahan "
			+ "FROM keluarga "
			+ "WHERE id = #{id_keluarga}")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"),
			@Result(property = "RT", column = "RT"),
			@Result(property = "RW", column = "RW"),
			@Result(property = "id_kelurahan", column = "id_kelurahan"),
			@Result(property = "kelurahan", column = "id", javaType = KelurahanModel.class, one = @One(select = "selectKelurahan")) })
	KeluargaModel selectKeluarga(@Param("id") String id);

	@Select("SELECT kelurahan.id, kelurahan.id_kecamatan, kelurahan.nama_kelurahan "
			+ "FROM kelurahan "
			+ "WHERE id = #{id_kelurahan}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "id_kecamatan", column = "id_kecamatan"),
			@Result(property = "nama_kelurahan", column = "nama_kelurahan"),
			@Result(property = "kecamatan", column = "id", javaType = KecamatanModel.class, one = @One(select = "selectKecamatan")) })
	KelurahanModel selectKelurahan(@Param("id") String id);
	
	@Select("SELECT kecamatan.id, kecamatan.nama_kecamatan, id_kota "
			+ "FROM kecamatan "
			+ "WHERE id = #{id_kecamatan}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nama_kecamatan", column = "nama_kecamatan"),
			@Result(property = "kota", column = "id", javaType = KotaModel.class, one = @One(select = "selectKota")) })
	KecamatanModel selectKecamatan(@Param("id") String id);
	
	@Select("SELECT kota.id, kota.nama_kota "
			+ "FROM kota "
			+ "WHERE id = #{id_kota}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nama_kota", column = "nama_kota") })
	KotaModel selectKota(@Param("id") String id);
	
}
