package com.gl.service;

import java.util.List;

import com.gl.entity.Student;

public interface StudentService {

	public List<Student> getAllStudents();

	public void saveStudent(Student student);

	public Student getStudentById(int studentId);

	public Student updateStudent(Student student);

	public void deleteStudentById(int studentId);

	public List<Student> searchBy(String firstName, String lastName);
}
