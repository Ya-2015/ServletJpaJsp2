package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SHOPPINGUSER database table.
 * 
 */
@Entity
@Table(name="SHOPPINGUSER", schema="testuserdb")
@NamedQuery(name="Shoppinguser.findAll", query="SELECT s FROM Shoppinguser s")
public class Shoppinguser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userid;

	private String email;

	private String username;

	private String userpwd;

	public Shoppinguser() {
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return this.userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

}