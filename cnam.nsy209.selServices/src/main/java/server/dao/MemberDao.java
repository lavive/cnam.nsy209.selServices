package server.dao;

import java.util.List;

import server.dao.entity.MemberEntity;
import server.dao.entity.SupplyDemandEntity;
import server.dao.entity.WealthSheetEntity;
import server.dao.interfaces.InterfaceDao;


public interface MemberDao extends InterfaceDao<MemberEntity>{

	public List<MemberEntity> getListLastMember(int sizeList);
	
	public List<MemberEntity> getAllMembers();
	
	public MemberEntity getMemberById(int id);
	
	public List<MemberEntity> getMembersByName(String name);
	
	public List<MemberEntity> getMembersByNameAndForname(String name,String forname);
	
	public List<MemberEntity> getMembersByTown(String town);
	
	public List<MemberEntity> getMembersBySupplyDemand(String type,String category);
	
	public List<SupplyDemandEntity> getSupplyDemands(int memberId);
	
	public WealthSheetEntity getWealthSheetEntity(int memberId);
	
	
}
