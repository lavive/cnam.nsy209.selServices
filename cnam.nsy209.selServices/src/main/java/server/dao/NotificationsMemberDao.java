package server.dao;

import java.util.List;

import server.dao.entity.NotificationEntity;
import server.dao.entity.NotificationsMemberEntity;
import server.dao.interfaces.InterfaceDao;


public interface NotificationsMemberDao extends InterfaceDao<NotificationsMemberEntity> {
	
	public List<NotificationsMemberEntity> getAllNotificationsMember();
	
	public void initialise();
	
	public List<NotificationEntity> getNotifications(int memberId);
	
	public void deleteNotification(int memberId, int notificationId);

}
