package server.dao.interfaces;

import server.notification.factory.NotificationFactory;

public interface InterfaceDao<ENTITY extends MarkerEntity> {
	
	public void create(ENTITY entity);
	
	public ENTITY get(ENTITY entity);
	
	public void update(ENTITY entity);
	
	public void delete(ENTITY entity);
	
	public NotificationFactory getNotificationFactory();

}
