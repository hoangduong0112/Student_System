package com.hd.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    public User(String fullName, String email, String password, Role userRole, String avatar) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.avatar = avatar;
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getUserRole().toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
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

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "user_role", length = 45)
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(name = "avatar", length = 200)
    private String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<OnlineService> onlineServices = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<RegSubject> regSubjects = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<SemeterSubject> semeterSubjects = new LinkedHashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;

}