package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.bo.StudentVO;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;
	
	/**
	 * API endpoint to create student and associate it with a class
	 * @param studentVO
	 */
	@PostMapping(value="students")
	public ResponseEntity createStudent(@RequestBody StudentVO studentVO){
		if(studentVO==null){
			LOGGER.error("Invalid request");
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Invalid request");
		}
		LOGGER.info("Request came for create student with id:{}",studentVO.getId());
		try {
			studentService.createStudent(studentVO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			if(e instanceof HttpClientErrorException){
				HttpClientErrorException clientErrorException=(HttpClientErrorException) e;
				HttpStatus httpStatus=clientErrorException.getStatusCode();
			return new ResponseEntity<Teacher>(httpStatus);
			}else{
				return new ResponseEntity<Teacher>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
