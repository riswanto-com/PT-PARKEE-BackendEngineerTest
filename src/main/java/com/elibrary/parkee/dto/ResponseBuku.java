package com.elibrary.parkee.dto;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBuku {
    private Integer id;
    @NotEmpty(message = "Judul Tidak Boleh Kosong")
    private String bukuJudul;
    @NotEmpty(message = "Penulis Tidak Boleh Kosong")
    private String bukuPenulis;
    @NotEmpty(message = "Diskripsi Tidak Boleh Kosong")
    private String bukuDiskripsi;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotNull(message = "Tahun Tidak Boleh Kosong")
    private Date bukuTahun;
}
