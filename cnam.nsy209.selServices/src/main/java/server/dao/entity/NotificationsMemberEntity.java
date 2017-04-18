package server.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import server.dao.interfaces.MarkerEntity;

@Entity
@Table(name = "notifications_member")
public class NotificationsMemberEntity implements MarkerEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	private MemberEntity member;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "notification_id")
	private List<NotificationEntity> notifications;
	

	/* getters and setters */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	public List<NotificationEntity> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationEntity> notifications) {
		this.notifications = notifications;
	}
	
	
}
