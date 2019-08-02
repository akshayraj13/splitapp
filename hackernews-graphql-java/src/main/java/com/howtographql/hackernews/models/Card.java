package com.howtographql.hackernews.models;

public class Card {
	private final String cardholdername;
    private final String cardnumber;
    private final String cardname;
    private final String month;
    private final String year;
    private final String cvv;
    private final String email;
    
	public Card(String cardholdername, String cardnumber, String cardname, String month, String year, String cvv,
			String email) {
		this.cardholdername = cardholdername;
		this.cardnumber = cardnumber;
		this.cardname = cardname;
		this.month = month;
		this.year = year;
		this.cvv = cvv;
		this.email = email;
	}
	public String getCardholdername() {
		return cardholdername;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public String getCardname() {
		return cardname;
	}
	public String getMonth() {
		return month;
	}
	public String getYear() {
		return year;
	}
	public String getCvv() {
		return cvv;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "Card [cardholdername=" + cardholdername + ", cardnumber=" + cardnumber + ", cardname=" + cardname
				+ ", month=" + month + ", year=" + year + ", cvv=" + cvv + ", email=" + email + "]";
	}

	

}
