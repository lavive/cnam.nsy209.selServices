package server.service;

import shared.dto.AssociationDto;


public interface ManageAssociation{
	
	public void createAssociation(AssociationDto associationDto);
	
	public void updateAssociation();
	
	public AssociationDto retrieveAssociation();

}
