package com.example.demo.transformers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.bo.StudentDTO;
import com.example.demo.model.Student;

@Component
public class StudentTransformer {

	
	public List<StudentDTO> trasformStudent(List<Student> students){
		List<StudentDTO> transformedStudents=new ArrayList<>();
		
		for(Student student:students){
			StudentDTO studentDTO=new StudentDTO();
			BeanUtils.copyProperties(student, studentDTO);
			transformedStudents.add(studentDTO);
		}
		
		return transformedStudents;
	}
}
