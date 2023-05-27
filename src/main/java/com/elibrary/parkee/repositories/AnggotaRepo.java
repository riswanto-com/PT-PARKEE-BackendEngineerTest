package com.elibrary.parkee.repositories;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elibrary.parkee.models.Anggota;

public interface AnggotaRepo extends JpaRepository<Anggota, Integer> {
    @Query("SELECT p FROM Anggota p WHERE p.anggotaEmail =:email")
    public Anggota findBukuByEmail(@PathParam("email") String email);
}
