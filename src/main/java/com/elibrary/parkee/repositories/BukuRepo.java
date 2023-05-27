package com.elibrary.parkee.repositories;


import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elibrary.parkee.models.Buku;

public interface BukuRepo extends JpaRepository<Buku, Integer> {
    @Query("SELECT p FROM Buku p WHERE p.bukuJudul =:judul")
    public Buku findBukuByName(@PathParam("judul") String judul);
}
