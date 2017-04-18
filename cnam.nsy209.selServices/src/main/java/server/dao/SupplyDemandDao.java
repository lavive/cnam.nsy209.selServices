package server.dao;

import java.util.List;

import server.dao.entity.SupplyDemandEntity;
import server.dao.interfaces.InterfaceDao;


public interface SupplyDemandDao extends InterfaceDao<SupplyDemandEntity>{
	
	public List<SupplyDemandEntity> getAllSupplyDemand();
	
//	public List<SupplyDemandEntity> getSupplyDemand(MemberEntity memberEntity);
//	
//	public List<SupplyDemandEntity> getSupplyDemand(int memberEntityId);
	
	public SupplyDemandEntity getSupplyDemandById(int id);

}
