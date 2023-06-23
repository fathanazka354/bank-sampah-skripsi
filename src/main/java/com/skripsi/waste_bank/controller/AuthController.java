package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.*;
import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.services.AdminService;
import com.skripsi.waste_bank.services.NasabahService;
import com.skripsi.waste_bank.services.SendImageService;
import com.skripsi.waste_bank.utils.Constant;
import jakarta.validation.Valid;
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

    @PostMapping("admin/email")
    public ResponseEntity<ResponseData<Admin>> getAdminByEmail(@RequestParam("email") String email){
        return adminService.findByEmail(email);
    }

    @PostMapping("admin/register")
    public ResponseEntity<ResponseData<Admin>> createAdmin(@RequestBody RegisterRequest admin){
        return adminService.createAdmin(admin);
    }

    @PutMapping("admin/update/{id}")
    public ResponseEntity<ResponseData<String>> updateAdmin(@PathVariable("id")Long id,
                                             @RequestParam(required = false) String firstName,
                                             @RequestParam(required = false) String lastName,
                                             @RequestParam(required = false) String email,
                                             @RequestParam(required = false) String password,
                                             @RequestParam(required = false) MultipartFile file) {

        Admin admin = new Admin();
        String url = "";
        if (file != null){
            url = sendImageService.uploadImage(file);
        }

        admin.setPassword(password);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmail(email);
        admin.setImgUrl(url);

        return adminService.updateAdmin(id, admin);
    }

    @PostMapping("admin/login")
    public ResponseEntity<ResponseData<AuthenticationResponse>> loginAdmin(@RequestParam(required = false) String username,
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

    @GetMapping("nasabah/active")
    public ResponseEntity<ResponseData<List<Nasabah>>> getAllNasabahActive(){
        return nasabahService.getAllNasabahActive();
    }

    @GetMapping("nasabah/not-active")
    public ResponseEntity<ResponseData<List<Nasabah>>> getAllNasabahNotActive(){
        return nasabahService.getAllNasabahNotActive();
    }

    @GetMapping("total")
    public ResponseEntity<ResponseData<ResponseTotal>> getAllNasabahTotal(){
        return nasabahService.getNasabahsTotal();
    }

    @GetMapping("nasabah/{id}")
    public ResponseEntity<ResponseData<Nasabah>> getNasabahById(@PathVariable("id") Long id){
        return nasabahService.getNasabahById(id);
    }
    @PostMapping("nasabah/email")
    public ResponseEntity<ResponseData<Nasabah>> getNasabahByEmail(@RequestParam("email") String email){
        return nasabahService.getByEmail(email);
    }

    @PostMapping("nasabah/login")
    public ResponseEntity<ResponseData<AuthenticationResponse>> loginNasabah(
                                                               @RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String password){
        return nasabahService.login( email, password);
    }

    @PostMapping("nasabah/register")
    public ResponseEntity<ResponseData<Nasabah>> createNasabah(@RequestBody @Valid NasabahDTO nasabahDto){
//        Nasabah nasabah = modelMapper.map(nasabahDto, Nasabah.class);
        return nasabahService.createNasabah(nasabahDto);
    }

    @PutMapping("nasabah/update/{id}")
    public ResponseEntity<ResponseData<String>> updateNasabah(@PathVariable("id") Long id,
                                                               @RequestParam(required = false) String firstName,
                                                               @RequestParam(required = false) String lastName,
                                                               @RequestParam(required = false) String email,
                                                               @RequestParam(required = false) String password,
                                                               @RequestParam(required = false) String address,
                                                               @RequestParam(required = false) Double tabungan,
                                                               @RequestParam(required = false) String telephone,
                                                               @RequestParam(required = false) Boolean isDeleted){
        Nasabah nasabah = new Nasabah();
        nasabah.setPassword(password);
        nasabah.setFirstName(firstName);
        nasabah.setLastName(lastName);
        nasabah.setEmail(email);
        nasabah.setAddress(address);
        nasabah.setTelephone(telephone);
        nasabah.setTabungan(tabungan);
        nasabah.setDeleted(isDeleted);
        return nasabahService.updateNasabahById(id,nasabah);
    }

    @PostMapping("nasabah/update-foto/{id}")
    public ResponseEntity<ResponseData<String>> updateNasabahFoto(@PathVariable("id") Long id,
                                                               @RequestParam(required = false) MultipartFile file){
        Nasabah nasabah = new Nasabah();
        String url = "";
        if (file != null){
            url = sendImageService.uploadImage(file);
        }

        nasabah.setImgUrl(url);
        return nasabahService.updateNasabahById(id,nasabah);
    }

    @PutMapping("nasabah/update-tabungan/{id}")
    public ResponseEntity<ResponseData<String>> updateTabunganNasabah(@PathVariable("id") Long id,
                                                               @RequestParam(required = false) Double tabungan){
        return nasabahService.updateTabunganNasabahById(id,tabungan);
    }

    @DeleteMapping("nasabah/delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteNasabahById(@PathVariable("id") Long id){
        return nasabahService.deleteNasabahById(id);
    }
}
