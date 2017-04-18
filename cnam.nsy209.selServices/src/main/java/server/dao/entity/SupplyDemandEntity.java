package server.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import server.dao.constantes.EnumSupplyDemand;
import server.dao.interfaces.MarkerEntity;

@Entity
@Table(name = "supply_demand")
public class SupplyDemandEntity implements MarkerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "type", nullable = false)
	private EnumSupplyDemand type;

	@Column(name = "category")
	private String category;

	@Column(name = "title")
	private String title;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	private MemberEntity member;	


	/* getter and setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EnumSupplyDemand getType() {
		return type;
	}

	public void setType(EnumSupplyDemand type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}


}
