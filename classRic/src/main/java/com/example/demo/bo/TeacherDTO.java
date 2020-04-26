package com.example.demo.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDTO {
	
	@JsonProperty
	private int id;
	
	@JsonProperty
	private String teacherName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	

}
