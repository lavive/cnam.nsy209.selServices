package server.notification.suscriber.interceptor.strategy.bean;

import javax.inject.Inject;
import javax.inject.Singleton;

import server.dao.NotificationsMemberDao;
import server.notification.suscriber.Suscriber;
import server.notification.suscriber.interceptor.strategy.SuscriberInterceptorAppStrategy;

@Singleton
public class SuscriberInterceptorAppStrategyBean implements SuscriberInterceptorAppStrategy {
	
	@Inject
	NotificationsMemberDao notificationsMemberDao;

	@Override
	public void execute(Suscriber suscriber) {
		/**
		 * rajouter une condition vérifiant si le membre possède l'appli mobile
		 */
		
		SuscriberUtil.upDateNotificationsMember(suscriber, this.notificationsMemberDao);

	}

}
