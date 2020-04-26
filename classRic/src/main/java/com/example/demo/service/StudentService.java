package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.bo.StudentVO;
import com.example.demo.model.Classroom;
import com.example.demo.model.Student;
import com.example.demo.model.dao.ClassRepository;
import com.example.demo.model.dao.StudentRepository;

@Service
public class StudentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Transactional
	public void createStudent(StudentVO studentVO) throws Exception{
		//find if student id that is student with same roll number already exists
		
		Optional<Student> studentExisting=studentRepository.findById(studentVO.getId());
		
		if(studentExisting.isPresent()){
			LOGGER.error("Student already exists");
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Student already exists");
		}
		
		//find if class is valid
		
	    Optional<Classroom> classroomexists=classRepository.findById(studentVO.getClassroomId());
	    
	    if(!classroomexists.isPresent()){
	    	LOGGER.error("Invalid classroom");
	    	throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Invalid classroom");
	    }else{
	    	Student student=new Student();
	    	student.setId(studentVO.getId());
	    	student.setStudentName(studentVO.getStudentName());
	    	Classroom classroom = classroomexists.get();
	    	List<Student> studentsInClassroom=classroom.getStudents();
	    	studentsInClassroom.add(student);
	    	classroom.setStudents(studentsInClassroom);
	    	student.setClassroom(classroom);
	    	Student studentSaved=studentRepository.save(student);
	    	LOGGER.info("Student successfully saved.");
	    	
	    	
	    }
	}

}
