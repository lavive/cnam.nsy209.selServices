package server.dao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;

@Entity
@Table(name = "catalog")
public class CatalogEntity implements MarkerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "modification_date", nullable = false)
	private Date modificationDate;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<GeneralOfferEntity> offers;


	/* getter and setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public List<GeneralOfferEntity> getOffers() {
		return offers;
	}

	public void setOffers(List<GeneralOfferEntity> offers) {
		this.offers = offers;
	}
}
