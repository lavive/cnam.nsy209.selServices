package server.service;

import java.util.List;

import shared.dto.SupplyDemandDto;


public interface ManageSupplyDemand{
	
	public List<SupplyDemandDto> retrieveAllSupplyDemands();
	
	public SupplyDemandDto retrieveSupplyDemand(int supplyDemanId);
	
	public void deleteSupplyDemand(int supplyDemanId);

}
