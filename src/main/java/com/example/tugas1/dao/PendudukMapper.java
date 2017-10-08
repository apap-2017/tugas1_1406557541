package com.example.tugas1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.PendudukModel;

@Mapper
public interface PendudukMapper {
	
	@Select("SELECT *"
			+ " FROM penduduk p"
			+ " JOIN keluarga k"
				+ " ON p.id_keluarga = k.id"
			+ " JOIN kelurahan kl"
				+ " ON k.id_kelurahan = kl.id"
			+ " JOIN kecamatan kc"
				+ " ON kl.id_kecamatan = kc.id"
			+ " JOIN kota kt"
				+ " ON kc.id_kota = kt.id"
			+ " WHERE nik = #{nik}")
	@Results(value = { @Result(property = "nik", column = "nik"), @Result(property = "nama", column = "nama"),
			@Result(property = "tempat_lahir", column = "tempat_lahir"),
			@Result(property = "tanggal_lahir", column = "tanggal_lahir"),
			@Result(property = "id_keluarga", column = "id_keluarga"),
			@Result(property = "keluarga", column = "id", javaType = KeluargaModel.class, one = @One(select = "selectKeluarga")) })
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Select("select keluarga.id, keluarga.nomor_kk, keluarga.alamat, keluarga.RT, keluarga.RW, keluarga.id_kelurahan, keluarga.is_tidak_berlaku from keluarga where id = #{id}")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"), @Result(property = "RT", column = "RT"),
			@Result(property = "RW", column = "RW"), @Result(property = "id_kelurahan", column = "id_kelurahan"),
			@Result(property = "is_tidak_berlaku", column = "is_tidak_berlaku")})
	//tambahain result Kelurahan, dan method selectKelurahan etc huhu mgr
	KeluargaModel selectKeluarga(@Param("id") String id);

	
	
}
