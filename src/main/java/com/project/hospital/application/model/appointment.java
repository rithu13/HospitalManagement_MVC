package com.project.hospital.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class appointment {
	private long id;
	private String user;
	private String hospital;
	private String doctor;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getHospital() {
		return user;
	}
	public void setHospital(String user) {
		this.user = user;
	}
	public String getDoctor() {
		return user;
	}
	public void setDoctor(String user) {
		this.user = user;
	}	
}
	
	