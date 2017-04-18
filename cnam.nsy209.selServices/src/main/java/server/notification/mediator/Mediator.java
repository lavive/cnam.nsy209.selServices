package server.notification.mediator;

import java.util.List;

import server.dao.entity.NotificationEntity;
import server.notification.suscriber.Suscriber;


public interface Mediator {
	
	public List<Suscriber> getSuscribers();
	
	public void sendNotification(NotificationEntity notification);
}
