package com.splitmybill.model;

public class Card {

	private String email;
	private String month;
	private int year;
	private int cvv;
	private long cardnumber;
	private int cardid;
	private String cardholdername;
	private String cardname;
	private int accbal;

	public Card() {
	}

	public int getAccbal() {
		return accbal;
	}

	public void setAccbal(int accbal) {
		this.accbal = accbal;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public long getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(long cardnumber) {
		this.cardnumber = cardnumber;
	}

	public int getCardid() {
		return cardid;
	}

	public void setCardid(int cardid) {
		this.cardid = cardid;
	}

	public String getCardholdername() {
		return cardholdername;
	}

	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
	}

}
