package com.ymg.locadorafilmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Date;
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="HISTORY")
public class History extends AbstractPersistable<Long>  {
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="MOVIE_ID")
    private Movie movie;

    @Column(name="IS_RENEWED", nullable = false)
    private Boolean isRenewed;

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


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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


    public Boolean isIsRenewed() {
        return this.isRenewed;
    }

    public Boolean getIsRenewed() {
        return this.isRenewed;
    }

    public void setIsRenewed(Boolean isRenewed) {
        this.isRenewed = isRenewed;
    }


}
