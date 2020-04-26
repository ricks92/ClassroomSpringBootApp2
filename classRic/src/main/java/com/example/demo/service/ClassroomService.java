package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.bo.ClassroomDTO;
import com.example.demo.bo.StudentDTO;
import com.example.demo.bo.TeacherDTO;
import com.example.demo.model.Classroom;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.model.dao.ClassRepository;
import com.example.demo.transformers.StudentTransformer;
import com.example.demo.transformers.TeacherTransformer;

@Service
public class ClassroomService {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomService.class);

	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private StudentTransformer studentTransformer;
	
	@Autowired
	private TeacherTransformer teacherTransformer;
	
	@Transactional
	public List<Student> getStudentsInAClass(int id){
		Optional<Classroom> classroom=classRepository.findById(id);
		
		List<Student> students=new ArrayList<>();
		
		if(classroom.isPresent()){
			students=classroom.get().getStudents();
		}else{
			LOGGER.error("Class does not exist.");
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Class does not exist.");
		}
		
		return students;
	}
	
	@Transactional
	public List<ClassroomDTO> getAllClasses(){
		List<ClassroomDTO> responseList=new ArrayList<>();
		List<Classroom> classrooms=classRepository.findAll();
		
		for(Classroom classroom:classrooms){
			ClassroomDTO classroomDTO=new ClassroomDTO();
			BeanUtils.copyProperties(classroom, classroomDTO);
			responseList.add(classroomDTO);
			
			
//			classroomDTO.setClassId(classroom.getId());
//			List<Student> students=classroom.getStudents();
//			List<Teacher> teachers=classroom.getTeachers();
//			
//			List<StudentDTO> studentDTOs=studentTransformer.trasformStudent(students);
//			List<TeacherDTO> teacherDTOs=teacherTransformer.transformTeacher(teachers);
//			classroomDTO.setStudents(studentDTOs);
//			classroomDTO.setTeachers(teacherDTOs);
			
			
		}
		
		return responseList;
	}
	
//	public List<StudentDTO> studentTransformer(List<Student> students){
//		List<StudentDTO> transformedStudents=new ArrayList<>();
//		
//		for(Student student:students){
//			StudentDTO studentDTO=new StudentDTO();
//			BeanUtils.copyProperties(student, studentDTO);
//			transformedStudents.add(studentDTO);
//		}
//		
//		return transformedStudents;
//	}
//	
//	
//	public List<TeacherDTO> teacherTransformer(List<Teacher> teachers){
//		List<TeacherDTO> transformedTeachers=new ArrayList<>();
//		
//		for(Teacher teacher:teachers){
//			TeacherDTO teacherDTO=new TeacherDTO();
//			BeanUtils.copyProperties(teacher, teacherDTO);
//			transformedTeachers.add(teacherDTO);
//		}
//		
//		return transformedTeachers;
//	}
}
