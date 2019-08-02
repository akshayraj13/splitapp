package com.howtographql.hackernews.models;

public class Card {
	private final String id;
    private final String name;
    private final String email;
    private final String password;
    
	public Card(String id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
