package server.notification.factory;

import server.dao.entity.MemberEntity;
import server.dao.entity.SupplyDemandEntity;


public interface NotificationSupplyDemandFactory extends NotificationFactory {

	public void setMemberOrigin(MemberEntity member);
		
	public void setNewDemand(SupplyDemandEntity demand);
	
	public void setNewSupply(SupplyDemandEntity supply);
}
