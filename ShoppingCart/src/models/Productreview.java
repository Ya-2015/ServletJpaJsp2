package models;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PRODUCTREVIEW database table.
 * 
 */
@Entity
@Table(name="PRODUCTREVIEW", schema="testuserdb")
@NamedQuery(name="Productreview.findAll", query="SELECT p FROM Productreview p")
public class Productreview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="post_seq")
    @SequenceGenerator(schema="testuserdb", name="post_seq", sequenceName="post_seq", allocationSize=1)
	private int reviewid;

	private String post;

	@Temporal(TemporalType.DATE)
	private Date postdate;

	private int poststar;

	private int productid;

	private String username;

	public Productreview() {
	}

	public int getReviewid() {
		return this.reviewid;
	}

	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getPostdate() {
		return this.postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public int getPoststar() {
		return this.poststar;
	}

	public void setPoststar(int poststar) {
		this.poststar = poststar;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}