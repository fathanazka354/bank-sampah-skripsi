package com.skripsi.waste_bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skripsi.waste_bank.utils.Constant;
import com.skripsi.waste_bank.utils.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "nasabah_tb")
public class Nasabah implements UserDetails {
    @Id
    @JoinColumn(name = "id_nasabah")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNasabah;

    @Column(name = "first_name")
    @NotBlank(message = "first name can Not blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "last name can Not blank")
    private String lastName;

    @Column(name = "email", unique = true)
    @Email(message = "Value Must Email")
    @NotBlank(message = "Email can Not blank")
    private String email;

    @JoinColumn(name = "password")
    @Size(min = 6,message = "Size must equal than 6 character")
    @NotBlank(message = "Password can Not blank")
    private String password;

    @JoinColumn(name = "address")
    @NotBlank(message = "address can Not blank")
    private String address;

    @Column(name = "image_url")
    private String imgUrl = Constant.DEFAULT_URL;

    @Value("false")
    @JoinColumn(name = "is_deleted")
    private boolean isDeleted;

    @JoinColumn(name = "role")
    private Role role;

    @OneToMany(mappedBy = "nasabah")
    @JsonIgnore
    private List<Information> information;

    @OneToMany(mappedBy = "nasabah")
    @JsonIgnore
    private List<AmbilTabungan> ambilTabungans;

    @CreationTimestamp
    @JoinColumn(name = "created_at",updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JoinColumn(name = "updated_at")
    private Date updatedAt;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
