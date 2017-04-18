package shared.dto;

import java.io.Serializable;



public class MessageDto implements Serializable{
	
	/**
	 * For checking version
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String title;
	
	private String text;
	
	private boolean state;
	
	private PersonDto transmitterPerson;
	

	/* getter and setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public PersonDto getTransmitterPerson() {
		return transmitterPerson;
	}

	public void setTransmitterPerson(PersonDto transmitterPerson) {
		this.transmitterPerson = transmitterPerson;
	}

}
