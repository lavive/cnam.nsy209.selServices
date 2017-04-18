package server.dao;

import java.util.List;

import server.dao.entity.NotificationEntity;
import server.dao.interfaces.InterfaceDao;

	
public interface NotificationDao extends InterfaceDao<NotificationEntity> {
	
	public List<NotificationEntity> create(List<NotificationEntity> notifications);
	
	public NotificationEntity getNotification(int notificationId);
	
}
