package server.notification.factory;

import java.util.List;

import server.dao.entity.NotificationEntity;


public interface NotificationFactory {
	
	public List<NotificationEntity> create();

}
