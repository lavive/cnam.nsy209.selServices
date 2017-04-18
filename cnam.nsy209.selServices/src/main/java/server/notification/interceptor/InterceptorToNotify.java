package server.notification.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.Transactional;

import server.dao.NotificationDao;
import server.dao.interfaces.InterfaceDao;
import server.dao.interfaces.MarkerEntity;
import server.notification.factory.NotificationFactoryContext;

@Transactional
public class InterceptorToNotify {
	
	@Inject
	NotificationFactoryContext notificationContext;
	
	@Inject
	NotificationDao notificationDao;
	
	@AroundInvoke
	public Object notify(InvocationContext ic) throws Exception {
		Object result = ic.proceed();
				
		notificationDao.create(notificationContext.create(((InterfaceDao<MarkerEntity>) ic.getTarget()).getNotificationFactory()));
		
		return result;
	}

}
