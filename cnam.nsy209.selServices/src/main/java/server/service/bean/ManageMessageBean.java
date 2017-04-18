package server.service.bean;

import javax.inject.Inject;

import server.dao.MessageDao;
import server.service.ManageMessage;
import server.service.transform.DtoToEntity;
import shared.dto.MessageDto;


public class ManageMessageBean implements ManageMessage {
	
	@Inject
	private MessageDao messageDao;

	

	@Override
	public void sendMessage(MessageDto messageDto) {
		
		messageDao.create(DtoToEntity.messageDtoToEntity(messageDto));
		
	}

}
