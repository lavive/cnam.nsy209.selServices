package server.notification.suscriber.interceptor.strategy;

import server.notification.suscriber.Suscriber;


public interface SuscriberInterceptorStrategyContext {
	
	public void execute(Suscriber suscriber, String Strategy);

}
