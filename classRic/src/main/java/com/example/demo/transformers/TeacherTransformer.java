package com.example.demo.transformers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.demo.bo.TeacherDTO;
import com.example.demo.model.Teacher;

@Component
public class TeacherTransformer {

	public List<TeacherDTO> transformTeacher(List<Teacher> teachers){
		List<TeacherDTO> transformedTeachers=new ArrayList<>();
		
		for(Teacher teacher:teachers){
			TeacherDTO teacherDTO=new TeacherDTO();
			BeanUtils.copyProperties(teacher, teacherDTO);
			transformedTeachers.add(teacherDTO);
		}
		
		return transformedTeachers;
	}
}
