package server.notification.factory;

import server.dao.entity.MessageEntity;


public interface NotificationMessageFactory extends NotificationFactory {
	
	public void setMessage(MessageEntity message);

}
