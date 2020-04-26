package com.example.demo.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Classroom;

@Repository
public interface ClassRepository extends JpaRepository<Classroom, Integer>{
	
//	@Query("select classroom from Classroom classroom LEFT JOIN FETCH classroom.students")
//	public List<Classroom> getAllClassrooms();

}
