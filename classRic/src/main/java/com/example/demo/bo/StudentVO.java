package com.example.demo.bo;

/**
 * View Object/Request Object
 * @author richa
 *
 */
public class StudentVO {

	private int id;
	
	private int classroomId;
	
	private String studentName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}
	
	
}
