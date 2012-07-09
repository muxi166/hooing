package com.ocb.pojo;

import java.sql.Date;

import android.R.integer;

public class Restaurant {
	private int restaurantID;
	private String 	restanrantAddr;
	private String restaurantType;
	private String restaurantIntro;
	private String restaurantLogoPhoto;
	private String restaurantPhone;
	private String drivingDirections;
	private String specialDish;
	private String favorNote;
	private double restaurantLat;
	private double restaurantLng;
	private String wfoodSMSNum;
	private int isSwipingCard;
	private int isBYO;
	private int parkingNum;
	private double scoefficient ;
	private Date deadline;
	private String rnFullSpelling;
	private String rnAbbr;
	private String restaurantPhotos;
	public int getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestanrantAddr() {
		return restanrantAddr;
	}
	public void setRestanrantAddr(String restanrantAddr) {
		this.restanrantAddr = restanrantAddr;
	}
	public String getRestaurantIntro() {
		return restaurantIntro;
	}
	public void setRestaurantIntro(String restaurantIntro) {
		this.restaurantIntro = restaurantIntro;
	}
	public String getRestaurantLogoPhoto() {
		return restaurantLogoPhoto;
	}
	public void setRestaurantLogoPhoto(String restaurantLogoPhoto) {
		this.restaurantLogoPhoto = restaurantLogoPhoto;
	}
	public String getRestaurantPhone() {
		return restaurantPhone;
	}
	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}
	public String getFavorNote() {
		return favorNote;
	}
	public void setFavorNote(String favorNote) {
		this.favorNote = favorNote;
	}
	public double getRestaurantLat() {
		return restaurantLat;
	}
	public void setRestaurantLat(double restaurantLat) {
		this.restaurantLat = restaurantLat;
	}
	public double getRestaurantLng() {
		return restaurantLng;
	}
	public void setRestaurantLng(double restaurantLng) {
		this.restaurantLng = restaurantLng;
	}
	public String getRestaurantPhotos() {
		return restaurantPhotos;
	}
	public void setRestaurantPhotos(String restaurantPhotos) {
		this.restaurantPhotos = restaurantPhotos;
	}
	private String restaurantName;

	
	


}
