package server.notification.suscriber.interceptor.strategy.bean;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import server.notification.suscriber.Suscriber;
import server.notification.suscriber.interceptor.strategy.SuscriberInterceptorAppStrategy;
import server.notification.suscriber.interceptor.strategy.SuscriberInterceptorEmailStrategy;
import server.notification.suscriber.interceptor.strategy.SuscriberInterceptorSmsStrategy;
import server.notification.suscriber.interceptor.strategy.SuscriberInterceptorStrategy;
import server.notification.suscriber.interceptor.strategy.SuscriberInterceptorStrategyContext;

@Singleton
public class SuscriberInterceptorStrategyContextBean implements SuscriberInterceptorStrategyContext {
	
	@Inject
	private SuscriberInterceptorAppStrategy suscriberInterceptorAppStrategy;
	
	@Inject
	private SuscriberInterceptorEmailStrategy suscriberInterceptorEmailStrategy;
	
	@Inject
	private SuscriberInterceptorSmsStrategy suscriberInterceptorSmsStrategy;
	
	
	private Map<String,SuscriberInterceptorStrategy> mapSuscriberInterceptorStrategy
			= new HashMap<String,SuscriberInterceptorStrategy>();
	
	private SuscriberInterceptorStrategyContextBean(){
		this.mapSuscriberInterceptorStrategy.put(EnumSuscriberInterceptorStrategy.APP_LONG_POLLING_STRATEGY.getWording(),
				suscriberInterceptorAppStrategy);
		this.mapSuscriberInterceptorStrategy.put(EnumSuscriberInterceptorStrategy.EMAIL_JAVAMAIL_STRATEGY.getWording(),
				suscriberInterceptorEmailStrategy);
		this.mapSuscriberInterceptorStrategy.put(EnumSuscriberInterceptorStrategy.SMS_LONG_POLLING_STRATEGY.getWording(),
				suscriberInterceptorSmsStrategy);
	}
	
	@Override
	public void execute(Suscriber suscriber, String Strategy) {
		getSuscriberInterceptorStrategy(Strategy).execute(suscriber);
		
	}
	
	
	private SuscriberInterceptorStrategy getSuscriberInterceptorStrategy(String key){
		return mapSuscriberInterceptorStrategy.get(key);
	}

}
