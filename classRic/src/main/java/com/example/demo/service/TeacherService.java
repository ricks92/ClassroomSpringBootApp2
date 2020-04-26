package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.model.Classroom;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.model.dao.ClassRepository;
import com.example.demo.model.dao.TeacherRepository;

@Service
public class TeacherService {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(TeacherService.class);

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Transactional
	public Teacher createTacher(@RequestBody Teacher teacher){
		
		//check if teacher already exists
		
		Optional<Teacher> teacherExists=teacherRepository.findById(teacher.getId());
		if(teacherExists.isPresent()){
			LOGGER.error("Teacher already exists");
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Teacher already exists");
		}
		
		Teacher savedTeacher=teacherRepository.save(teacher);
		LOGGER.info("Teacher successfully saved");
		
		return savedTeacher;
	}
	
	public void assignTeacherToClass(int teacherId,int classId){
		Optional<Teacher> teacher=teacherRepository.findById(teacherId);
		if(!teacher.isPresent()){
			LOGGER.error("Teacher does not exist");
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Teacher does not exist.");
		}
		
       Optional<Classroom> classroom=classRepository.findById(classId);
		
		if(!classroom.isPresent()){
			LOGGER.error("Class does not exist.");
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Class does not exist.");
		}
		
		List<Teacher> teachers=classroom.get().getTeachers();
		
		if(teachers.stream().anyMatch(t->t.getId()==teacherId)){
			LOGGER.error("Teacher is already in this class.");
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Teacher is already assigned to this class.");
		}
		
		teachers.add(teacher.get());
		classroom.get().setTeachers(teachers);
		classRepository.save(classroom.get());
		LOGGER.info("Teacher successfully assigned to the requested classroom");
		
		
	}
}
