package shared.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentDto implements Serializable{
	
	/**
	 * For checking version
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private MemberDto creditorMember;

	private MemberDto debtorMember;

	private BigDecimal amount;
	

	/* getter and setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MemberDto getCreditorMember() {
		return creditorMember;
	}

	public void setCreditorMember(MemberDto creditorMember) {
		this.creditorMember = creditorMember;
	}

	public MemberDto getDebtorMember() {
		return debtorMember;
	}

	public void setDebtorMember(MemberDto debtorMember) {
		this.debtorMember = debtorMember;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	

}
