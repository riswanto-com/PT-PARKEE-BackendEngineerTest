package com.elibrary.parkee.controlllers;

import java.util.Date;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elibrary.parkee.dto.ResponseData;
import com.elibrary.parkee.models.Buku;
import com.elibrary.parkee.models.Peminjaman;
import com.elibrary.parkee.services.PeminjamanService;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {
    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping
    public Iterable<Peminjaman> findAll() {
        return peminjamanService.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Peminjaman findId(@PathVariable("id") Long id) {
        return peminjamanService.findOne(id);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> removeOne(@PathVariable("id") Long id) {
        ResponseData<String> responseData = new ResponseData<>();
        peminjamanService.removeOne(id);
        responseData.setStatus(true);
        responseData.getMessages().add("Delete Data Berhasil");
        responseData.setData(null);
        return ResponseEntity.ok(responseData);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<ResponseData<Peminjaman>> create(@Valid @RequestBody Peminjaman peminjaman, Errors errors) {
        ResponseData<Peminjaman> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        peminjamanService.save(peminjaman);
        responseData.setStatus(true);
        responseData.setData(null);
        responseData.getMessages().add("Data Berhasil Tersimpan");
        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Peminjaman>> update(@Valid @RequestBody Peminjaman peminjaman, Errors errors) {
        ResponseData<Peminjaman> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Peminjaman update = new Peminjaman();
        update.setPeminjamanTanggalPinjam(peminjaman.getPeminjamanTanggalPinjam());
        update.setPeminjamanLama(peminjaman.getPeminjamanLama());
        update.setAnggota(peminjaman.getAnggota());
        update.setPeminjamanId(peminjaman.getPeminjamanId());
        peminjamanService.save(update);
        responseData.getMessages().add("Update Peminjaman Sukses");
        responseData.setStatus(true);
        responseData.setData(null);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/pengembalian")
    public ResponseEntity<ResponseData<Peminjaman>> pengembalian(@Valid @RequestBody Peminjaman peminjaman,
            Errors errors) {
        ResponseData<Peminjaman> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Peminjaman pinjam = peminjamanService.findOne(peminjaman.getPeminjamanId());
        if (pinjam != null) {
            Date currentDate = peminjaman.getPeminjamanTanggalKembali();
            Date tanggalPinjam = pinjam.getPeminjamanTanggalPinjam();
            long diff = currentDate.getTime() - tanggalPinjam.getTime();
            long pengembalian = diff / (24 * 60 * 60 * 1000);
            Integer lamaPinjam = pinjam.getPeminjamanLama();
            String statusPinjam = "on-time";
            if (pengembalian > lamaPinjam) {
                statusPinjam = "telat";
            }
            Peminjaman update = new Peminjaman();
            update.setPeminjamanStatus(statusPinjam);
            update.setPeminjamanTanggalKembali(currentDate);
            responseData.setData(null);
            responseData.setStatus(true);
            responseData.getMessages().add("Status Pengembalian Buku "+statusPinjam);
            return ResponseEntity.ok(responseData);
        }
        responseData.setStatus(false);
        responseData.getMessages().add("Data Tidak Ada");
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addSupplier(@RequestBody Buku buku, @PathVariable("id") Long productId) {
        ResponseData<String> responseData = new ResponseData<>();
        Peminjaman cekBuku=peminjamanService.findByBukuId(buku.getId());
        System.out.println(cekBuku);
        if(cekBuku !=null){
            responseData.setStatus(false);
            responseData.getMessages().add("Buku Sudah Ada Di List Peminjaman");
            responseData.setData(null);
            return ResponseEntity.ok(responseData);
        }
        peminjamanService.addBuku(buku, productId);
        responseData.setStatus(true);
        responseData.getMessages().add("Buku Masuk List Peminjaman");
        responseData.setData(null);
        return ResponseEntity.ok(responseData);
    }

}
