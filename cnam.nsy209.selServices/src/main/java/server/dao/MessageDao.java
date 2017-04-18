package server.dao;

import java.util.List;

import server.dao.entity.MessageEntity;
import server.dao.entity.PersonEntity;
import server.dao.interfaces.InterfaceDao;


public interface MessageDao extends InterfaceDao<MessageEntity>{
	
	public List<MessageEntity> getMessageByState(boolean state);
	
	public List<MessageEntity> getMessages();
	
	public List<MessageEntity> getMessages(PersonEntity personEntity);
	
	public MessageEntity getMessageById(int id);
	
	public void deleteMessage();
	
	public void deleteMessages(List<MessageEntity> messageEntities);
	

}
