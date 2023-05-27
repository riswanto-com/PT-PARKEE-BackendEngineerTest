package com.elibrary.parkee.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Peminjaman implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long peminjamanId;
    private Integer PeminjamanLama;
    @Temporal(TemporalType.DATE)
    private Date peminjamanTanggalPinjam;
    @Temporal(TemporalType.DATE)
    private Date peminjamanTanggalKembali;
    @Column(length = 15)
    private String peminjamanStatus;
    @ManyToOne
    private Anggota anggota;
    @ManyToMany
    @JoinTable(
        name = "peminjaman_detail",
        joinColumns = @JoinColumn(name="det_pinjam_id_peminjaman"),
        inverseJoinColumns = @JoinColumn(name="det_pinjam_id_buku")
    )
    // @JsonManagedReference
    private Set<Buku> bukus;

}
