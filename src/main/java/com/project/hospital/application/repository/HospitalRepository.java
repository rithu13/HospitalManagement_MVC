package com.project.hospital.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.hospital.application.model.Doctor;

public interface HospitalRepository extends JpaRepository<Doctor, Long>{
	 @Query(value = "select * from doctor m where m.doctorname like %:keyword%", nativeQuery = true)
	 List<Doctor> findByKeyword(@Param("keyword") String keyword);
	 @Query(value = "select * from doctor m where m.doctorname like %:keyword% limit 1", nativeQuery = true)
	 Doctor findByMovieName(@Param("keyword") String keyword);
}
