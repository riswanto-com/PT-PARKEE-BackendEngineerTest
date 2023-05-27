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

import com.elibrary.parkee.dto.ResponseAnggota;
import com.elibrary.parkee.dto.ResponseData;
import com.elibrary.parkee.models.Anggota;
import com.elibrary.parkee.services.AnggotaService;
@RestController
@RequestMapping("/api/anggota")
public class AnggotaController {
    @Autowired
    private AnggotaService anggotaService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Iterable<Anggota> findAll() {
        return anggotaService.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Anggota findOne(@PathVariable("id") Integer id) {
        return anggotaService.findOne(id);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<ResponseData<Anggota>> create(@Valid @RequestBody ResponseAnggota anggotaData, Errors errors) {
        ResponseData<Anggota> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.ok(responseData);
        }
        Anggota cekEmail=anggotaService.findByEmail(anggotaData.getAnggotaEmail());
        if(cekEmail !=null){
            responseData.getMessages().add("Email Sudah Ada");
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.ok(responseData);
            
        }
        Anggota anggota = mapper.map(anggotaData, Anggota.class);
        responseData.setStatus(true);
        responseData.setData(anggotaService.save(anggota));
        return ResponseEntity.ok(responseData);
    }
    @CrossOrigin
    @PutMapping
    public ResponseEntity<ResponseData<Anggota>> update(@Valid @RequestBody ResponseAnggota anggotaData, Errors errors) {
        ResponseData<Anggota> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.ok(responseData);
        }
        Anggota anggota = mapper.map(anggotaData, Anggota.class);
        responseData.setStatus(true);
        responseData.setData(anggotaService.save(anggota));
        return ResponseEntity.ok(responseData);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> removeOne(@PathVariable("id") Integer id) {
        ResponseData<String> responseData = new ResponseData<>();
        anggotaService.removeOne(id);
        responseData.setStatus(true);
        responseData.getMessages().add("Delete Data Berhasil");
        responseData.setData(null);
        return ResponseEntity.ok(responseData);
    }
}
