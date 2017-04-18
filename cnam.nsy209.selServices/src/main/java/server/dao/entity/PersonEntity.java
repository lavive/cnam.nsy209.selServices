package server.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "person")
public class PersonEntity implements MarkerEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_person")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "town")
	private String town;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "cell_number")
	private String cellNumber;

	@Column(name = "phone_number")
	private String phoneNumber;
	

	/* getter and setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
