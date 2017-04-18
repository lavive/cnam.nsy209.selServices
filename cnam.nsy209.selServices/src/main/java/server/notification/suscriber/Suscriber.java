package server.notification.suscriber;

import java.util.List;

import server.dao.entity.MemberEntity;
import server.dao.entity.NotificationEntity;


public interface Suscriber {
	
	public void update(NotificationEntity notification);
	
	public List<MemberEntity> getMembersToNotify();
	
	public NotificationEntity getNotification();
	
	
	
}
