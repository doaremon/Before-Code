package com.denglu;

public class Spinjie {
	private String username;
	private String userpwd;
	@Override
	public String toString() {
		return "Spinjie [username=" + username + ", userpwd=" + userpwd + "]";
	}
	public Spinjie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Spinjie(String username, String userpwd) {
		super();
		this.username = username;
		this.userpwd = userpwd;
	}
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
}