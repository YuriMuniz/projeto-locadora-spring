package com.ymg.locadorafilmes.model;

import java.util.List;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;


import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="RENTAL")
public class Rental extends AbstractPersistable<Long> {    

    
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "RENTAL_MOVIE",
	joinColumns = {@JoinColumn(name = "ID_RENTAL", referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name = "ID_MOVIE", referencedColumnName = "id")}
	)	
	private List<Movie> movies;

    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name="USER_ID")
	private User user;

    @Temporal(TemporalType.TIMESTAMP)
	@Column (name = "RETURN_DATE", nullable = false)
	private Date returnDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Zagreb")
    @Temporal(TemporalType.TIMESTAMP)
	@Column (name = "RETURN_DATE_RENEWED", nullable = true)
	private Date returnDateRenewed;

    @Column (name="IS_RENEWED", nullable=false)
    private Boolean isRenewed;

    @Column (name="IS_RETURNED", nullable=false)
    private Boolean isReturned;

    
    @Column (name="COUNT_RENEWED", nullable=false)
    private Integer countRenewed;

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

    public Boolean isIsRenewed() {
        return this.isRenewed;
    }

    public Boolean getIsRenewed() {
        return this.isRenewed;
    }

    public void setIsRenewed(Boolean isRenewed) {
        this.isRenewed = isRenewed;
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


    public List<Movie> getMovies() {
        return this.movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDateRenewed() {
        return this.returnDateRenewed;
    }

    public void setReturnDateRenewed(Date returnDateRenewed) {
        this.returnDateRenewed = returnDateRenewed;
    }

    public Boolean isIsReturned() {
        return this.isReturned;
    }

    public Boolean getIsReturned() {
        return this.isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

  

    public Integer getCountRenewed() {
        return this.countRenewed;
    }

    public void setCountRenewed(Integer countRenewed) {
        this.countRenewed = countRenewed;
    }

 

    
}
