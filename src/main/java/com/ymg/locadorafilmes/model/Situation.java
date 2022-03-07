package com.ymg.locadorafilmes.model;

public enum Situation {
    ACTIVE("ACTIVE"),    
    UNAVAILABLE("UNAVAILABLE"),
    INATIVE("INATIVE");
    
	private String description;
	
    private Situation(String description){
    	this.description = description;
    }
    
    @Override
    public String toString() {
    	
    	return description;
    }
}
