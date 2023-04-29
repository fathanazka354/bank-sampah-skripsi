//package com.skripsi.waste_bank.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.skripsi.waste_bank.utils.Constant;
//import com.skripsi.waste_bank.utils.Role;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "user_tb")
//public class User implements UserDetails {
//    @Id
//    @JoinColumn(name = "idUser")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idUser;
//
//    @NotBlank(message = "Username must be filled")
//    @Column(name = "username",unique = true)
//    @Size(min = 5,message = "Size char must be more 5")
//    private String username;
//
//    @Email(message = "Value must be Email")
//    @NotBlank(message = "Email cannot null")
//    @Column(name = "email",unique = true)
//    private String email;
//
//    @NotBlank(message = "Password must not be null")
//    @Size(min = 6,message = "Password must equal than 6 character")
//    private String password;
//
//    @Column(name = "image_url")
//    private String imgUrl = Constant.DEFAULT_URL;
//
//    @JoinColumn(name = "address")
//    @NotBlank(message = "address can Not blank")
//    private String address;
//
//    @Column(name = "isActive")
//    private boolean isActive = true;
//
//    @Column(name = "role")
//    private Role role;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<AmbilTabungan> ambilTabungans;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Information> information;
//
//    @CreationTimestamp
//    @JoinColumn(name = "created_at",updatable = false)
//    private Date createdAt;
//
//    @UpdateTimestamp
//    @JoinColumn(name = "updated_at")
//    private Date updatedAt;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
