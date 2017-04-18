package server.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;


@Entity
@PrimaryKeyJoinColumn(name="id_person")
@Table(name = "association")
public class AssociationEntity extends PersonEntity implements MarkerEntity{
	
	@Column(name = "website")
	private String website;

	

	/* getter and setter */
	

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
		
}

