package br.gov.fabricasocial.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserModel {

	@Id
	@GeneratedValue
	private Integer idUser;
	private String username;
	private String password;
	
	public Integer getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Integer id) {
		this.idUser = id;
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
	
}
