package com.elibrary.parkee.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.parkee.models.Buku;
import com.elibrary.parkee.repositories.BukuRepo;

@Service
@Transactional
public class BukuService {
    @Autowired
    private BukuRepo bukuRepo;
    public Buku save(Buku buku) {
        return bukuRepo.save(buku);
    }
    public Buku findOne(Integer id) {
        Optional<Buku> buku = bukuRepo.findById(id);
        if (!buku.isPresent()) {
            return null;
        }
        return buku.get();
    }
    public Buku findByNama(String name){
        return bukuRepo.findBukuByName(name);
    }
    public Iterable<Buku> findAll() {
        return bukuRepo.findAll();
    }
    public void removeOne(Integer id) {
        bukuRepo.deleteById(id);
    }
}
