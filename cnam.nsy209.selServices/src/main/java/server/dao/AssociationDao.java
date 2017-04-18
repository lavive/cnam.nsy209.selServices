package server.dao;

import server.dao.entity.AssociationEntity;
import server.dao.interfaces.InterfaceDao;


public interface AssociationDao extends InterfaceDao<AssociationEntity> {
	
	AssociationEntity getAssociation();

}
