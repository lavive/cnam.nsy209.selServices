package server.notification.factory;

import java.util.Map;

import server.dao.constantes.EnumAssociationAttribute;

public interface NotificationAssociationFactory extends NotificationFactory {

	public void setMapAttributeValue(Map<EnumAssociationAttribute,String> mapAttributeValue);
}
