package server.notification.suscriber.interceptor.strategy.bean;



public enum EnumSuscriberInterceptorStrategy {
	APP_LONG_POLLING_STRATEGY("mobile app long polling strategy"),
	EMAIL_JAVAMAIL_STRATEGY("email javaMail strategy"),
	SMS_LONG_POLLING_STRATEGY("mobile app long polling strategy");
	
	String wording;
	EnumSuscriberInterceptorStrategy(String wording){
		this.wording = wording;
	}
	
	public String getWording(){
		return this.wording;
	}
	
	public static EnumSuscriberInterceptorStrategy getByWording(String wording){
		for(EnumSuscriberInterceptorStrategy enumStrategy : values()){
			if(enumStrategy.getWording().equals(wording))
				return enumStrategy;
		}
		
		return null;
	}
}

