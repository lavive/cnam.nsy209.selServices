package server.notification.suscriber.bean;

import java.util.List;

import javax.inject.Singleton;
import javax.interceptor.Interceptors;
import javax.transaction.Transactional;

import server.dao.entity.MemberEntity;
import server.dao.entity.NotificationEntity;
//import server.notification.publish.NotificationPublish;
//import server.notification.publish.NotificationTopicPublish;
import server.notification.suscriber.Suscriber;

@Singleton
public class SuscriberBean implements Suscriber {
	
	private List<MemberEntity> membersToNotify; 
	
	private NotificationEntity notification;

	@Override
	@Transactional
	@Interceptors({ server.notification.suscriber.interceptor.SuscriberForAppInterceptor.class,
					server.notification.suscriber.interceptor.SuscriberForSMSInterceptor.class,
					server.notification.suscriber.interceptor.SuscriberForEmailInterceptor.class}) 
	public void update(NotificationEntity notification) {
		this.membersToNotify = notification.getMembresToNotify();
		this.notification = notification;

	}

	@Override
	public List<MemberEntity> getMembersToNotify() {
		return this.membersToNotify;
	}

	@Override
	public NotificationEntity getNotification() {
		return this.notification;
	}

}
