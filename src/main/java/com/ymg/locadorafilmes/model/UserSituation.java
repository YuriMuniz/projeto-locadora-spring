package com.ymg.locadorafilmes.model;

public enum UserSituation {
    ACTIVE("ACTIVE"),    
    INATIVE("INATIVE");
    
	private String description;
	
    private UserSituation(String description){
    	this.description = description;
    }
    
    @Override
    public String toString() {
    	
    	return description;
    }
}
