package com.example.tugas1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
private int id;
private String nik;
private String nama;
private String tempat_lahir;
private String tanggal_lahir;
private String jenis_kelamin;
private boolean is_wni;
private String id_keluarga;
private String agama;
private String pekerjaan;
private String status_perkawinan;
private String status_dalam_keluarga;
private String golongan_darah;
private boolean is_wafat;
private KeluargaModel keluarga;
private KelurahanModel kelurahan;
private KecamatanModel kecamatan;
private KotaModel kota;
}
