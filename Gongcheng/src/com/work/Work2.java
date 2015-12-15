package com.work;

public class Work2 {
private String username;
private String userpwd;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpwd() {
	return userpwd;
}
public void setUserpwd(String userpwd) {
	this.userpwd = userpwd;
}
@Override
public String toString() {
	return "Work2 [username=" + username + ", userpwd=" + userpwd + "]";
}
public Work2(String username, String userpwd) {
	super();
	this.username = username;
	this.userpwd = userpwd;
}
}
