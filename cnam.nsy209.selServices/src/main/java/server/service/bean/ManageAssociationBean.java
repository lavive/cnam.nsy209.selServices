package server.service.bean;

import javax.inject.Inject;

import server.dao.AssociationDao;
import server.service.ManageAssociation;
import server.service.transform.DtoToEntity;
import server.service.transform.EntityToDto;
import shared.dto.AssociationDto;


public class ManageAssociationBean implements ManageAssociation {
	
	@Inject
	private AssociationDao associationDao;

	
	@Override
	public void createAssociation(AssociationDto associationDto) {
		
		associationDao.create(DtoToEntity.associationDtoToEntity(associationDto));

	}

	@Override
	public void updateAssociation() {
		
		associationDao.update(associationDao.getAssociation());
		
	}

	@Override
	public AssociationDto retrieveAssociation() {
		
		return EntityToDto.associationEntityToDto(associationDao.getAssociation());
				
	}

}
