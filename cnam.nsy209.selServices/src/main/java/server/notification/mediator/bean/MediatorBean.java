package server.notification.mediator.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import server.dao.entity.NotificationEntity;
import server.notification.mediator.Mediator;
import server.notification.suscriber.Suscriber;

@Singleton
public class MediatorBean implements Mediator {

	private List<Suscriber> suscribers = new ArrayList<Suscriber>();
	
	@Inject
	private Suscriber suscriber;


	@Override
	public List<Suscriber> getSuscribers() {
		this.suscribers.clear();
		this.suscribers.add(this.suscriber);
		return this.suscribers;
	}

	@Override
	public void sendNotification(NotificationEntity notification) {
		for(Suscriber suscriber:getSuscribers()){
			suscriber.update(notification);
		}
		
	}


}
