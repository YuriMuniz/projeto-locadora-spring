package com.ymg.locadorafilmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.Builder;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER")
public class User extends AbstractPersistable<Long> {

    @NotEmpty(message = "Please provide a name")
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

   
    @NotEmpty(message = "Please provide a email")
    @Email(message = "Please provide valid email")
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = true)
    private String password;

    @Column(name = "GENDER", nullable = true)
    private String gender;

    @NotEmpty(message = "Please provide a CPF")
    @CPF(message="Please provide valid CPF")
    @Column(name = "CPF", nullable = false, unique=true)
    private String cpf;

    
    @NotNull(message = "Please provide a birth date")
    @Temporal(TemporalType.TIMESTAMP)
	@Column (name = "BIRTH_DATE", nullable = false)
	private Date birthDate;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Profile> userProfiles;

    @Enumerated(EnumType.STRING)
    @Column(name = "situation", nullable = false)
    private UserSituation situation;

    @PrePersist
    protected void prePersist() {
        if (this.createdDate == null)
            createdDate = new Date().toInstant();
        if (this.updatedDate == null)
            updatedDate = new Date().toInstant();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedDate = new Date().toInstant();
    }

    public List<Profile> getUserProfiles() {
        return userProfiles;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserProfiles(List<Profile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public UserSituation getSituation() {
        return this.situation;
    }

    public void setSituation(UserSituation situation) {
        this.situation = situation;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
