package com.work;

public class Work {
private String content;
private String fatherid;
@Override
public String toString() {
	return "Work [content=" + content + ", fatherid=" + fatherid + ", id=" + id
			+ ", recipient=" + recipient + ", recipientid=" + recipientid
			+ ", send_time=" + send_time + ", sender=" + sender + ", senderid="
			+ senderid + ", sims_type=" + sims_type + ", title=" + title + "]";
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getFatherid() {
	return fatherid;
}
public void setFatherid(String fatherid) {
	this.fatherid = fatherid;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getRecipient() {
	return recipient;
}
public void setRecipient(String recipient) {
	this.recipient = recipient;
}
public String getRecipientid() {
	return recipientid;
}
public void setRecipientid(String recipientid) {
	this.recipientid = recipientid;
}
public String getSend_time() {
	return send_time;
}
public void setSend_time(String send_time) {
	this.send_time = send_time;
}
public String getSender() {
	return sender;
}
public void setSender(String sender) {
	this.sender = sender;
}
public String getSenderid() {
	return senderid;
}
public void setSenderid(String senderid) {
	this.senderid = senderid;
}
public String getSims_type() {
	return sims_type;
}
public void setSims_type(String sims_type) {
	this.sims_type = sims_type;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
private String id;
private String recipient;
private String recipientid;
private String send_time;
private String sender;
private String senderid;
private String sims_type;
private String title;
}
