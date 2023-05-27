package com.elibrary.parkee.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseAnggota {
    private Integer id;
    @Email(message = "Email Tidak Valid")
    @NotEmpty(message = "Email Tidak Boleh Kosong")
    private String anggotaEmail;
    @NotEmpty(message = "Nama Tidak Boleh Kosong")
    private String anggotaNama;
    @NotEmpty(message = "Telepon Tidak Boleh Kosong")
    @Length(max = 13,message = "Telepon Harus 12 atau 13 Angka",min = 12)
    private String anggotaTelepon;
    @NotEmpty(message = "Alamat Tidak Boleh Kosong")
    private String anggotaAlamat;
}
