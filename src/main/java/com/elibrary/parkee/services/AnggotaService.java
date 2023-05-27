package com.elibrary.parkee.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.parkee.models.Anggota;
import com.elibrary.parkee.repositories.AnggotaRepo;

@Service
@Transactional
public class AnggotaService {
    @Autowired
    private AnggotaRepo anggotaRepo;
    public Anggota save(Anggota anggota) {
        return anggotaRepo.save(anggota);
    }
    public Anggota findOne(Integer id) {
        Optional<Anggota> anggota = anggotaRepo.findById(id);
        if (!anggota.isPresent()) {
            return null;
        }
        return anggota.get();
    }
    public Iterable<Anggota> findAll() {
        return anggotaRepo.findAll();
    }
    public Anggota findByEmail(String email){
        return anggotaRepo.findBukuByEmail(email);
    }
    public void removeOne(Integer id) {
        anggotaRepo.deleteById(id);
    }
}
