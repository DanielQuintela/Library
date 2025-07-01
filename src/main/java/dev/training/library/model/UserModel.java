package dev.training.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password;
    private LocalDate birth;

    private LocalDate memberSince;

    @PrePersist
    public void prePersist() {
        this.memberSince = LocalDate.now();
    }
    public UserModel(Long id, String email, String name, String password, LocalDate birth, LocalDate memberSince) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.memberSince = memberSince;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }
}
