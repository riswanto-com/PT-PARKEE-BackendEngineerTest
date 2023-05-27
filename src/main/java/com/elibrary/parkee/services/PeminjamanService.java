package com.elibrary.parkee.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elibrary.parkee.models.Buku;
import com.elibrary.parkee.models.Peminjaman;
import com.elibrary.parkee.repositories.PeminjamanRepo;

@Service
@Transactional
public class PeminjamanService {
    @Autowired
    private PeminjamanRepo peminjamanRepo;
    @Autowired private BukuService bukuService;
    public Peminjaman save(Peminjaman peminjaman) {
        return peminjamanRepo.save(peminjaman);
    }

    public Peminjaman findOne(Long id) {
        Optional<Peminjaman> peminjaman = peminjamanRepo.findById(id);
        if (!peminjaman.isPresent()) {
            return null;
        }
        return peminjaman.get();
    }

    public Iterable<Peminjaman> findAll() {
        return peminjamanRepo.findAll();
    }
    public void removeOne(Long id) {
        peminjamanRepo.deleteById(id);
    }
    public Peminjaman findByBukuId(Integer bukuId) {
        Buku buku =bukuService.findOne(bukuId);
        if(buku ==null){
            return null;
        }      
        return peminjamanRepo.findPeminjamanByBuku(buku);
    }
    public void addBuku(Buku buku, Long peminjamanId) {
        Peminjaman peminjaman = findOne(peminjamanId);
        if (peminjaman == null) {
            throw new RuntimeException("Product with Id" + peminjamanId + " not Found");
        }
        peminjaman.getBukus().add(buku);
        save(peminjaman);
    }
}
