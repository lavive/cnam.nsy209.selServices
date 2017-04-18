package server.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;

@Entity
@Table(name = "message")
public class MessageEntity implements MarkerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "message_subject_id")
//	private MessageSubjectEntity subject;

	@Column(name = "text")
	private String text;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "state")
	private boolean state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private PersonEntity transmitterPerson;
	
	@Column(name = "date", nullable = false)
	private Date date;
	
	/* getter and setter */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public MessageSubjectEntity getSubject() {
//		return subject;
//	}
//
//	public void setSubject(MessageSubjectEntity subject) {
//		this.subject = subject;
//	}

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

	public PersonEntity getTransmitterPerson() {
		return transmitterPerson;
	}

	public void setTransmitterPerson(PersonEntity person) {
		this.transmitterPerson = person;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
