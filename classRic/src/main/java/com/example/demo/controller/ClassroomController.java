package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.ClassroomDTO;
import com.example.demo.model.Classroom;
import com.example.demo.model.Student;
import com.example.demo.service.ClassroomService;

@RestController
public class ClassroomController {

	@Autowired
	private ClassroomService classroomService;
	
	@GetMapping(value="studentInClass/{id}",produces="application/json")
	public List<Student> getStudentsInAClass(@PathVariable("id")int id){
		return classroomService.getStudentsInAClass(id);
	}
	
	@GetMapping(value="classrooms")
	public List<ClassroomDTO> getAllClasses(){
		return classroomService.getAllClasses();
	}
}
