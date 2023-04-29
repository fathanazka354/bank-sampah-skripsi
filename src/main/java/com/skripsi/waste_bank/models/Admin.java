package com.skripsi.waste_bank.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skripsi.waste_bank.utils.Constant;
import com.skripsi.waste_bank.utils.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Entity@Table(name = "admin_tb")@Builder
public class Admin implements UserDetails {

    @Id
    @JoinColumn(name = "idAdmin")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;

    @NotBlank(message = "first name must be filled")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "last name must be filled")
    @Column(name = "lastName")
    private String lastName;

    @Email(message = "Value must be Email")
    @NotBlank(message = "Email cannot null")
    @Column(name = "email",unique = true)
    private String email;

    @NotBlank(message = "Password must not be null")
    @Size(min = 6,message = "Password must equal than 6 character")
    private String password;

    @Column(name = "image_url")
    private String imgUrl = Constant.DEFAULT_URL;

    @Column(name = "isActive")
    private boolean isActive = true;

    @Column(name="role")
    private Role role;

    @OneToMany(mappedBy = "admin")
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
