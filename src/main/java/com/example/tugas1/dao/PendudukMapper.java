package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	/*
	 * property - nama list/var di model; column - yang samanya
	 */
	
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
			})
	PendudukModel selectPendudukAja (@Param("nik") String nik);
	
	@Select ("SELECT id, nik, nama, tempat_lahir, tanggal_lahir, id_keluarga, golongan_darah, agama, status_perkawinan, status_dalam_keluarga, pekerjaan, is_wni, is_wafat "
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
			@Result(property = "status_dalam_keluarga", column = "status_dalam_keluarga"),
			@Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "is_wni", column = "is_wni"),
			@Result(property = "is_wafat", column = "is_wafat"),
			@Result(property = "keluarga", column = "id_keluarga", javaType = KeluargaModel.class, one = @One(select = "selectKeluarga")) })
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Select("SELECT id, nomor_kk, alamat, RT, RW, id_kelurahan "
			+ "FROM keluarga "
			+ "WHERE id = #{id_keluarga}")
	@Results(value = { @Result(property = "id", column = "id"),
			@Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"),
			@Result(property = "RT", column = "RT"),
			@Result(property = "RW", column = "RW"),
			@Result(property = "id_kelurahan", column = "id_kelurahan"),
			@Result(property = "kelurahan", column = "id_kelurahan", javaType = KelurahanModel.class, one = @One(select = "selectKelurahan")) })
	KeluargaModel selectKeluarga(@Param("id") int id);

	@Select("SELECT id, id_kecamatan, nama_kelurahan "
			+ "FROM kelurahan "
			+ "WHERE id = #{id_kelurahan}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nama_kelurahan", column = "nama_kelurahan"),
			@Result(property = "kecamatan", column = "id_kecamatan", javaType = KecamatanModel.class, one = @One(select = "selectKecamatan")) })
	KelurahanModel selectKelurahan(@Param("id") int id);
	
	@Select("SELECT id, nama_kecamatan, id_kota "
			+ "FROM kecamatan "
			+ "WHERE id = #{id_kecamatan}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nama_kecamatan", column = "nama_kecamatan"),
			@Result(property = "kota", column = "id_kota", javaType = KotaModel.class, one = @One(select = "selectKota")) })
	KecamatanModel selectKecamatan(@Param("id") int id);
	
	@Select("SELECT id, nama_kota "
			+ "FROM kota "
			+ "WHERE id = #{id_kota}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "nama_kota", column = "nama_kota") })
	KotaModel selectKota(@Param("id") int id);
	
	
	/*
	 * Method deletePenduduk untuk mengubah status wafat
	 */
	@Update("UPDATE penduduk SET is_wafat = 1 "
			+ "WHERE nik = #{nik}")
    void deletePenduduk (String nik);
	
	
	/*
	 * Method addPenduduk untuk menambah penduduk ke database
	 */
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) "
			+ "VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
	void addPenduduk(PendudukModel penduduk);
	
	
	/*
	 * Method updatePenduduk untuk mengubah data penduduk
	 */
	@Update("UPDATE penduduk "
			+ "SET nik=#{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, golongan_darah = #{golongan_darah} "
			+ "WHERE id = #{id}")
    void updatePenduduk(PendudukModel penduduk);

	@Select("SELECT nik, nama, jenis_kelamin FROM penduduk JOIN keluarga ON penduduk.id_keluarga = keluarga.id JOIN "
			+ "kelurahan ON keluarga.id_kelurahan = kelurahan.id JOIN kecamatan ON kelurahan.id_kecamatan = kecamatan.id JOIN "
			+ "kota ON kecamatan.id_kota = kota.id WHERE keluarga.id_kelurahan = #{id_kelurahan}")
	List<PendudukModel> selectPendudukByIdKelurahan(String id_kelurahan);
	
	@Select("SELECT DISTINCT nama, nik, tanggal_lahir, (YEAR(CURDATE())-YEAR(tanggal_lahir)) AS usia FROM penduduk JOIN "
			+ "keluarga ON penduduk.id_keluarga = keluarga.id WHERE keluarga.id_kelurahan = #{id_kelurahan} ORDER BY usia ASC limit 1")
	 	PendudukModel pendudukTermuda(String id_kelurahan);
	
	@Select("SELECT DISTINCT nama, nik, tanggal_lahir, (YEAR(CURDATE())-YEAR(tanggal_lahir)) AS usia FROM penduduk JOIN "
			+ "keluarga ON penduduk.id_keluarga = keluarga.id WHERE keluarga.id_kelurahan = #{id_kelurahan} ORDER BY usia DESC limit 1")
	PendudukModel pendudukTertua(String id_kelurahan);
}