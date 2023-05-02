package com.project.hospital.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.hospital.application.model.Doctor;
import com.project.hospital.application.model.appointment;



public interface Appointment extends JpaRepository<appointment, Long> {
	 @Query(value = "select * from appointment m where m.user like %:keyword%", nativeQuery = true)
	 List<appointment> findByKeyword(@Param("keyword") String keyword);

	void save(Doctor appointment);
}