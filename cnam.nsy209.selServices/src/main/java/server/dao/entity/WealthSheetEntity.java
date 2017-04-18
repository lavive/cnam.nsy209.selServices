package server.dao.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;

@Entity
@Table(name = "wealth_sheet")
public class WealthSheetEntity implements MarkerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy= "wealthSheet")
	private MemberEntity member;

	@Column(name = "initial_account", nullable = false)
	private BigDecimal initialAccount;

	@Column(name = "final_account", nullable = false)
	private BigDecimal finalAccount;

	@OneToMany(fetch = FetchType.EAGER)
	private List<PaymentEntity> payments;
	
	
	/* getter and setter */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	public BigDecimal getInitialAccount() {
		return initialAccount;
	}

	public void setInitialAccount(BigDecimal initialAccount) {
		this.initialAccount = initialAccount;
	}

	public BigDecimal getFinalAccount() {
		return finalAccount;
	}

	public void setFinalAccount(BigDecimal finalAccount) {
		this.finalAccount = finalAccount;
	}

	public List<PaymentEntity> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentEntity> payments) {
		this.payments = payments;
	}

	

}
