package server.service.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import server.dao.SupplyDemandDao;
import server.dao.entity.SupplyDemandEntity;
import server.service.ManageSupplyDemand;
import server.service.transform.EntityToDto;
import shared.dto.SupplyDemandDto;




public class ManageSupplyDemandBean implements ManageSupplyDemand {
	
	@Inject
	private SupplyDemandDao supplyDemandDao;

	
	@Override
	public List<SupplyDemandDto> retrieveAllSupplyDemands() {
		List<SupplyDemandDto> supplyDemands = new ArrayList<SupplyDemandDto>();
		for(SupplyDemandEntity supplyDemand:supplyDemandDao.getAllSupplyDemand()){
			supplyDemands.add(EntityToDto.supplyDemandEntityToDto(supplyDemand));
		}
		
		return supplyDemands;
	}

	@Override
	public SupplyDemandDto retrieveSupplyDemand(int supplyDemanId) {
		
		return EntityToDto.supplyDemandEntityToDto(supplyDemandDao.getSupplyDemandById(supplyDemanId));
	}

	@Override
	public void deleteSupplyDemand(int supplyDemanId) {
		
		supplyDemandDao.delete(supplyDemandDao.getSupplyDemandById(supplyDemanId));
	}

}
