package br.gov.fabricasocial.models;

public class User {
	
	int idUser;
	String username;
	String password;
	int userType;
	
	public User(String username, String password, int userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
}
