package com.howtographql.hackernews.models;

public class CardInput {
	
    private String email;
    
    public CardInput(){
    	
    }

	public CardInput(String email) {
		
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
    
}
