package server.dao.constantes;

public enum EnumAssociationAttribute {
	NAME("Nom"),
	TOWN("Ville"),
	ADDRESS("Adresse postale"),
	EMAIL("Adresse électronique"),
	PHONE_NUMBER("Numéro de téléphone fixe"),
	CELL_NUMBER("Numéro de téléphone portable"),
	WEBSITE("Adresse du site internet");
	
	String wording;
	EnumAssociationAttribute(String wording){
		this.wording = wording;
	}
	
	public String getWording(){
		return this.wording;
	}
	
	public static EnumAssociationAttribute getByWording(String wording){
		for(EnumAssociationAttribute enumAttribute : values()){
			if(enumAttribute.getWording().equals(wording))
				return enumAttribute;
		}
		
		return null;
	}
}
