package server.dao.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;

@Entity
@PrimaryKeyJoinColumn(name="id_person")
@Table(name = "member")
public class MemberEntity extends PersonEntity implements MarkerEntity{
	
	@Column(name = "forname")
	private String forname;
	
	@Column(name ="mobile_id",unique = true)
	private String mobileId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy= "member")
	private List<SupplyDemandEntity> supplyDemand;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wealth_sheet_id")
	private WealthSheetEntity wealthSheet;
	
	@Column(name = "date_modification", nullable = false)
	private Date dateModification;
	
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "membresToNotify")
	List<NotificationEntity> notifications;

	

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

	public List<SupplyDemandEntity> getSupplyDemand() {
		return supplyDemand;
	}

	public void setSupplyDemand(List<SupplyDemandEntity> supplyDemand) {
		this.supplyDemand = supplyDemand;
	}

	public WealthSheetEntity getWealthSheet() {
		return wealthSheet;
	}

	public void setWealthSheet(WealthSheetEntity wealthSheet) {
		this.wealthSheet = wealthSheet;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public List<NotificationEntity> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationEntity> notifications) {
		this.notifications = notifications;
	}
		
}
