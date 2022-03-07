package com.ymg.locadorafilmes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PROFILE")
public class Profile extends AbstractPersistable<Long> implements GrantedAuthority{

    private static final long serialVersionUID = -4600840153957593563L;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
	@JsonBackReference
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false, length = 10)
    private Role role;

    public Profile(Role role, User user){
        this.role = role;
        this.user = user;
    }	

    public Profile(){}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public String getAuthority() {
        return role.toString();
    }

}

