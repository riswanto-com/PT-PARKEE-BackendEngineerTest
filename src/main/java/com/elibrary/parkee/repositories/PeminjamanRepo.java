package com.elibrary.parkee.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elibrary.parkee.models.Buku;
import com.elibrary.parkee.models.Peminjaman;

public interface PeminjamanRepo extends JpaRepository<Peminjaman, Long> {

    @Query("SELECT p FROM Peminjaman p WHERE :buku MEMBER OF p.bukus")
    public Peminjaman findPeminjamanByBuku(Buku buku);
}
