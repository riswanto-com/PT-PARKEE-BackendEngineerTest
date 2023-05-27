package com.elibrary.parkee.controlllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.elibrary.parkee.dto.ResponseBuku;
import com.elibrary.parkee.dto.ResponseData;
import com.elibrary.parkee.dto.SearchData;
import com.elibrary.parkee.models.Buku;
import com.elibrary.parkee.services.BukuService;

@RestController
@RequestMapping("/api/buku")
public class BukuController {
    @Autowired
    private BukuService bukuService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Iterable<Buku> findAll() {
        return bukuService.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Buku findOne(@PathVariable("id") Integer id) {
        return bukuService.findOne(id);
    }
    @PostMapping("/byName")
    public Buku finBukuName(@RequestBody SearchData searchData) {
        return bukuService.findByNama(searchData.getSearchKey());
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<ResponseData<Buku>> create(@Valid @RequestBody ResponseBuku bukuData, Errors errors) {
        ResponseData<Buku> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.ok(responseData);
        }
        Buku cekJudul=bukuService.findByNama(bukuData.getBukuJudul());
        if(cekJudul !=null){
            responseData.getMessages().add("Judul Sudah Ada");
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.ok(responseData);
            
        }
        Buku buku = mapper.map(bukuData, Buku.class);
        responseData.setStatus(true);
        responseData.setData(bukuService.save(buku));
        return ResponseEntity.ok(responseData);
    }
    @CrossOrigin
    @PutMapping
    public ResponseEntity<ResponseData<Buku>> update(@Valid @RequestBody ResponseBuku bukuData, Errors errors) {
        ResponseData<Buku> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.ok(responseData);
        }
        Buku buku = mapper.map(bukuData, Buku.class);
        responseData.setStatus(true);
        responseData.setData(bukuService.save(buku));
        return ResponseEntity.ok(responseData);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> removeOne(@PathVariable("id") Integer id) {
        ResponseData<String> responseData = new ResponseData<>();
        bukuService.removeOne(id);
        responseData.getMessages().add("Delete Data Berhasil");
        responseData.setStatus(true);
        responseData.setData(null);
        return ResponseEntity.ok(responseData);
    }
}
