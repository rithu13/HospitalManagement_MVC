package com.project.hospital.application.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hospital.application.model.Doctor;
import com.project.hospital.application.model.appointment;
import com.project.hospital.application.repository.Appointment;


@Service
@Transactional
public class HospitalAppointmentService {
	@Autowired
	private Appointment repo;
	
	public List<appointment> listAll(){
		return repo.findAll();
	}
	
	public void save(appointment doctor) {
		repo.save(doctor);
	}
	
	public appointment get(long id) {
		return repo.findById(id).get();
	}
	public List<appointment> getByKeyword(String keyword){
		  keyword = keyword.toLowerCase();
		  return repo.findByKeyword(keyword);
	}
	public void delete(long id) {
		repo.deleteById(id);
	}
	
    public void createAppointment(Doctor appointment) {
        // Perform any business logic related to creating a new appointment

        // Call the repository layer to persist the appointment object to the database
    	repo.save(appointment);
    }
}
