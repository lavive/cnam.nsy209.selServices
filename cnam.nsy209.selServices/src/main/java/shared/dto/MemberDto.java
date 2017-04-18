package shared.dto;

import java.util.List;

public class MemberDto extends PersonDto{
	
	/**
	 * For checking version
	 */
	private static final long serialVersionUID = 1L;

	private String forname;
	
	private String mobileId;
	
	private List<SupplyDemandDto> supplyDemand;
	
	private WealthSheetDto wealthSheet;

	

	/* getter and setter */

	public String getForname() {
		return forname;
	}

	public void setForname(String forname) {
		this.forname = forname;
	}

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public List<SupplyDemandDto> getSupplyDemand() {
		return supplyDemand;
	}

	public void setSupplyDemand(List<SupplyDemandDto> supplyDemand) {
		this.supplyDemand = supplyDemand;
	}

	public WealthSheetDto getWealthSheet() {
		return wealthSheet;
	}

	public void setWealthSheet(WealthSheetDto wealthSheet) {
		this.wealthSheet = wealthSheet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	

}
