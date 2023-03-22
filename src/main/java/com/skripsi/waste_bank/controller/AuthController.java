package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.NasabahDTO;
import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.services.AdminService;
import com.skripsi.waste_bank.services.NasabahService;
import com.skripsi.waste_bank.services.SendImageService;
import com.skripsi.waste_bank.utils.Constant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    @Autowired
    private AdminService adminService;

    @Autowired private NasabahService nasabahService;
    @Autowired private SendImageService sendImageService;
    @Autowired private ModelMapper modelMapper;

//    admin
    @GetMapping("admin")
    public ResponseEntity<ResponseData<List<Admin>>> getAllAdmin(){
        return adminService.getAllAdmin();
    }


    @GetMapping("admin/{id}")
    public ResponseEntity<ResponseData<Admin>> getAdminById(@PathVariable("id") Long id){
        return adminService.getAdminById(id);
    }

    @PostMapping("admin/create")
    public ResponseEntity<ResponseData<Admin>> createAdmin(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }

    @PutMapping("admin/update/{id}")
    public ResponseEntity<ResponseData<String>> updateAdmin(@PathVariable("id")Long id,
                                             @RequestParam(required = false) String username,
                                             @RequestParam(required = false) String email,
                                             @RequestParam(required = false) String password,
                                             @RequestParam(required = false) MultipartFile file) {

        Admin admin = new Admin();
        String url = "";
        if (file != null){
            url = sendImageService.uploadImage(file);
        }

        admin.setPassword(password);
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setImgUrl(url);

        return adminService.updateAdmin(id, admin);
    }

    @PostMapping("admin/login")
    public ResponseEntity<ResponseData<Admin>> loginAdmin( @RequestParam(required = false) String username,
                                                               @RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String password){
        return adminService.login(username, email, password);
    }

    @DeleteMapping("admin/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteAdminById(@PathVariable("id") Long id){
        return adminService.deleteAdmin(id);
    }



//  nasabah
    @GetMapping("nasabah")
    public ResponseEntity<ResponseData<List<Nasabah>>> getAllNasabah(){
        return nasabahService.getAllNasabah();
    }

    @GetMapping("nasabah/{id}")
    public ResponseEntity<ResponseData<Nasabah>> getNasabahById(@PathVariable("id") Long id){
        return nasabahService.getNasabahById(id);
    }

    @PostMapping("nasabah/login")
    public ResponseEntity<ResponseData<Nasabah>> loginNasabah( @RequestParam(required = false) String username,
                                                               @RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String password){
        return nasabahService.login(username, email, password);
    }

    @PostMapping("nasabah/create")
    public ResponseEntity<ResponseData<Nasabah>> createNasabah(@RequestBody NasabahDTO nasabahDto){
        Nasabah nasabah = modelMapper.map(nasabahDto, Nasabah.class);
        return nasabahService.createNasabah(nasabah);
    }

    @PutMapping("nasabah/update/{id}")
    public ResponseEntity<ResponseData<Nasabah>> updateNasabah(@PathVariable("id") Long id,
                                                               @RequestParam(required = false) String username,
                                                               @RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String password,
                                                               @RequestParam(required = false) String address,
                                                               @RequestParam(required = false) MultipartFile file){
        Nasabah nasabah = new Nasabah();
        String url = "";
        if (file != null){
            url = sendImageService.uploadImage(file);
        }

        nasabah.setPassword(password);
        nasabah.setUsername(username);
        nasabah.setEmail(email);
        nasabah.setAddress(address);
        nasabah.setImgUrl(url);
        return nasabahService.updateNasabahById(id,nasabah);
    }

    @DeleteMapping("nasabah/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteNasabahById(@PathVariable("id") Long id){
        return nasabahService.deleteNasabahById(id);
    }
}
