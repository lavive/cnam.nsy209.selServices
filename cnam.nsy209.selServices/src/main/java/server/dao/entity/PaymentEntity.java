package server.dao.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;

@Entity
@Table(name = "payment")
public class PaymentEntity implements MarkerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private MemberEntity creditorMember;

	@ManyToOne(fetch=FetchType.EAGER)
	private MemberEntity debtorMember;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private SupplyDemandEntity supplyDemand;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	

	/* getter and setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MemberEntity getCreditorMember() {
		return creditorMember;
	}

	public void setCreditorMember(MemberEntity creditorMember) {
		this.creditorMember = creditorMember;
	}

	public MemberEntity getDebtorMember() {
		return debtorMember;
	}

	public void setDebtorMember(MemberEntity debtorMember) {
		this.debtorMember = debtorMember;
	}

	public SupplyDemandEntity getSupplyDemand() {
		return supplyDemand;
	}

	public void setSupplyDemand(SupplyDemandEntity supplyDemand) {
		this.supplyDemand = supplyDemand;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
