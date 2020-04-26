package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;

@RestController
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(value="teachers")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
		try{
		teacherService.createTacher(teacher);
		return new ResponseEntity<Teacher>(HttpStatus.ACCEPTED);
		}catch(Exception e ){
			if(e instanceof HttpClientErrorException){
				HttpClientErrorException clientErrorException=(HttpClientErrorException) e;
				HttpStatus httpStatus=clientErrorException.getStatusCode();
			return new ResponseEntity<Teacher>(httpStatus);
			}else{
				return new ResponseEntity<Teacher>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@GetMapping(value="teachers/{teacherId}/classroom/{classId}",produces="application/json")
	public  ResponseEntity assignTeacherToClass(@PathVariable("teacherId")int teacherId,@PathVariable("classId") int classId){
		try{
			teacherService.assignTeacherToClass(teacherId, classId);
			return new ResponseEntity(HttpStatus.ACCEPTED);
			}catch(Exception e ){
				if(e instanceof HttpClientErrorException){
					HttpClientErrorException clientErrorException=(HttpClientErrorException) e;
					HttpStatus httpStatus=clientErrorException.getStatusCode();
				return new ResponseEntity(httpStatus);
				}else{
					return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
	}
}
