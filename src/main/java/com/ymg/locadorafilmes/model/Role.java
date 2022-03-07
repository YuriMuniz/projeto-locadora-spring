package com.ymg.locadorafilmes.model;


public enum Role {
    USER("USER"),
    ADMIN("ADMIN");
        
	private String description;
	
    private Role(String description){
    	this.description = description;
    }
    
    @Override
    public String toString() {
    	
    	return description;
    }
}

