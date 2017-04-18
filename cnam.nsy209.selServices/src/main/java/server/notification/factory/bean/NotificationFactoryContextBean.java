package server.notification.factory.bean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import server.dao.entity.NotificationEntity;
import server.notification.factory.NotificationFactory;
import server.notification.factory.NotificationFactoryContext;
import server.notification.mediator.Mediator;

@Singleton
public class NotificationFactoryContextBean implements NotificationFactoryContext {
	
	@Inject
	private Mediator mediator;

	@Override
	public List<NotificationEntity> create(NotificationFactory notificationFactory) {
		List<NotificationEntity> notifications = notificationFactory.create();
		for(NotificationEntity notification:notifications){
			mediator.sendNotification(notification);
		}
		return notifications;
	}

}
