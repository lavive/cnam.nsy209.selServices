package server.notification.suscriber.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import server.notification.suscriber.Suscriber;
import server.notification.suscriber.interceptor.strategy.SuscriberInterceptorStrategyContext;
import server.notification.suscriber.interceptor.strategy.bean.EnumSuscriberInterceptorStrategy;

public class SuscriberForSMSInterceptor implements SuscriberInterceptor {
	
	@Inject
	SuscriberInterceptorStrategyContext suscriberInterceptorStrategyContext;
	
	@Override
	@AroundInvoke
	public Object interceptSuscriber(InvocationContext ic) throws Exception {
		Object result = ic.proceed();
		
		Suscriber suscriber = (Suscriber) ic.getTarget();
		
		suscriberInterceptorStrategyContext.execute(suscriber, 
				EnumSuscriberInterceptorStrategy.SMS_LONG_POLLING_STRATEGY.getWording());

		
		return result;
		
	}

}
