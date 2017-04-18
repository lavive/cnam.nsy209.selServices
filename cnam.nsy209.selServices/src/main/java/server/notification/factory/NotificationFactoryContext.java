package server.notification.factory;

import java.util.List;

import server.dao.entity.NotificationEntity;


public interface NotificationFactoryContext {	
	
	public List<NotificationEntity> create(NotificationFactory notificationFactory);

}
