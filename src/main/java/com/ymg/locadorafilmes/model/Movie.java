package com.ymg.locadorafilmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import java.util.Date;
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="MOVIE")
public class Movie extends AbstractPersistable<Long> {

    @NotEmpty(message = "Please provide a name")
    @Column(name="NAME", nullable=false)
    private String name;

    @NotEmpty(message = "Please provide a genre")
    @Column(name="GENRE", nullable=false)
    private String genre;

    @NotEmpty(message = "Please provide a director")
    @Column(name="DIRECTOR", nullable=false)
    private String director;

   
    @Column(name="AMOUNT", nullable=false)
    private Integer amount;
    
    @Column(name="AMOUNT_RENTED", nullable=true)
    private Integer amountRented;

   
    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedDate;

    @PrePersist
    protected void prePersist() {
        if (this.createdDate == null) createdDate = new Date().toInstant();
        if (this.updatedDate == null) updatedDate = new Date().toInstant();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedDate = new Date().toInstant();
    }


     
    public Situation getSituation() {
        return this.situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
    
    @Enumerated(EnumType.STRING)
    @Column(name="SITUATION", nullable = false)
    private Situation situation;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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


    public Integer getAmountRented() {
        return this.amountRented;
    }

    public void setAmountRented(Integer amountRented) {
        this.amountRented = amountRented;
    }

}
