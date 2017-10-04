package com.example.tugas1.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
private String id;
private String nik;
private String nama;
private String tempat_lahir;
private Date tanggal_lahir;
private String jenis_kelamin;
private byte is_wni;
private String id_keluarga;
private String agama;
private String pekerjaan;
private String status_perkawinan;
private String status_dalam_keluarga;
private String golongan_darah;
private byte is_wafat;
}