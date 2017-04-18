//package server.dao.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import server.dao.interfaces.MarkerEntity;
//
//@Entity
//@Table(name = "message_subject")
//public class MessageSubjectEntity implements MarkerEntity {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id")
//	private Integer id;
//	
//	@ManyToOne(fetch=FetchType.EAGER)
//	private MemberEntity transmitterMember;	
//	
////	@OneToOne(fetch = FetchType.EAGER, mappedBy="subject")
////	private MessageEntity message;
//
////	@Column(name = "type", nullable = false)
////	private String type;
//
//	@Column(name = "title")
//	private String title;
//
//
//	/* getter and setter */
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public MemberEntity getTransmitterMember() {
//		return transmitterMember;
//	}
//
//	public void setTransmitterMember(MemberEntity transmitterMember) {
//		this.transmitterMember = transmitterMember;
//	}
//
////	public MessageEntity getMessage() {
////		return message;
////	}
////
////	public void setMessage(MessageEntity message) {
////		this.message = message;
////	}
//
////	public String getType() {
////		return type;
////	}
////
////	public void setType(String type) {
////		this.type = type;
////	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	
//	
//
//}
