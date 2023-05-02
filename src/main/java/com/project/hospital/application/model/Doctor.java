package com.project.hospital.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Doctor {
	
	private long id;
	private String doctorname;
	private String doctorspecialization;
	private String doctordescription;
	private float doctorrating;
	private float doctorprice;
	private String doctorimg;
//	private String parentalrating;
//	private long seats;

	public Doctor() {
	}

	

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public String getDoctorspecialization() {
		return doctorspecialization;
	}

	public void setDoctorspecialization(String doctorspecialization) {
		this.doctorspecialization = doctorspecialization;
	}

	public String getDoctordescription() {
		return doctordescription;
	}

	public void setDoctordescription(String doctordescription) {
		this.doctordescription = doctordescription;
	}

	public float getDoctorrating() {
		return doctorrating;
	}

	public void setDoctorrating(float doctorrating) {
		this.doctorrating = doctorrating;
	}

	public float getDoctorprice() {
		return doctorprice;
	}

	public void setDoctorprice(float doctorprice) {
		this.doctorprice = doctorprice;
	}

	public String getDoctorimg() {
		return doctorimg;
	}

	public void setDoctorimg(String doctorimg) {
		this.doctorimg = doctorimg;
	}


	
  
	
}
