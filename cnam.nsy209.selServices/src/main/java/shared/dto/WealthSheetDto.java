package shared.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class WealthSheetDto implements Serializable{

	/**
	 * For checking version
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private BigDecimal initialAccount;

	private BigDecimal finalAccount;

	private List<PaymentDto> payments;
	
	
	/* getter and setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<PaymentDto> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentDto> payment) {
		this.payments = payment;
	}
}
