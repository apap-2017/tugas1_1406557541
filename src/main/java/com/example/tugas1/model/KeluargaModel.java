package com.example.tugas1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
private String id;
private String nomor_kk;
private String alamat;
private String RT;
private String RW;
private String id_kelurahan;
private byte is_tidak_berlaku;
private KelurahanModel kelurahan;
private KecamatanModel kecamatan;
private KotaModel kota;
private List<PendudukModel> anggotaKeluarga;
}
