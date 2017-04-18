package server.notification.factory;

import server.dao.entity.MemberEntity;


public interface NotificationMemberFactory extends NotificationFactory {
	
	public void setNewMember(MemberEntity member);
	public void setMemberLeaving(MemberEntity member);

}
