package com.project.hospital.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hospital.application.model.Doctor;
import com.project.hospital.application.repository.HospitalRepository;

@Service
@Transactional
public class HospitalService {
	@Autowired
	//private MovieRepository repo;
	private HospitalRepository repo;
	
	public List<Doctor> listAll(){
		return repo.findAll();
	}
	
	public void save(Doctor doctor) {
		repo.save(doctor);
	}
	
	public Doctor get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	public List<Doctor> getByKeyword(String keyword){
		  keyword = keyword.toLowerCase();
		  return repo.findByKeyword(keyword);
	}
	public List<Doctor> findByDoctorName(String keyword){
		  keyword = keyword.toLowerCase();
		  return repo.findByKeyword(keyword);
	}
}
