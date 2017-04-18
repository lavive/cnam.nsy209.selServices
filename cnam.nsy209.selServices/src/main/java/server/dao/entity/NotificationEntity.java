package server.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;

@Entity
@Table(name = "notification")
public class NotificationEntity implements MarkerEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "text")
	private String text;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "topic_id")
	private NotificationTopicEntity topic;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "notification_membre",joinColumns = @JoinColumn(name = "id_notification"),
              inverseJoinColumns = @JoinColumn(name = "id_person"))
	private List<MemberEntity> membresToNotify;

	
	/* getter and setter */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public NotificationTopicEntity getTopic() {
		return topic;
	}

	public void setTopic(NotificationTopicEntity topic) {
		this.topic = topic;
	}

	public List<MemberEntity> getMembresToNotify() {
		return membresToNotify;
	}

	public void setMembresToNotify(List<MemberEntity> membresToNotify) {
		this.membresToNotify = membresToNotify;
	}
	

}
