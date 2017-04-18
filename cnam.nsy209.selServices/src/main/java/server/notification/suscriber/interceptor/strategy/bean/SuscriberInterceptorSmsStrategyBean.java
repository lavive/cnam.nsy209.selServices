package server.notification.suscriber.interceptor.strategy.bean;

import javax.inject.Inject;
import javax.inject.Singleton;

import server.dao.NotificationsMemberDao;
import server.notification.suscriber.Suscriber;
import server.notification.suscriber.interceptor.strategy.SuscriberInterceptorSmsStrategy;

@Singleton
public class SuscriberInterceptorSmsStrategyBean implements SuscriberInterceptorSmsStrategy {
	
	@Inject
	NotificationsMemberDao notificationsMemberDao;

	@Override
	public void execute(Suscriber suscriber) {
		/**
		 * rajouter une condition vérifiant si le membre a renseigné son n° de portable
		 */
		
		SuscriberUtil.upDateNotificationsMember(suscriber, this.notificationsMemberDao);		

	}

}
