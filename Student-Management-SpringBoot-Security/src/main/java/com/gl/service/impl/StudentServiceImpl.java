package com.gl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dao.StudentRepository;
import com.gl.entity.Student;
import com.gl.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);
	}

	@Override
	public Student getStudentById(int studentId) {
		// TODO Auto-generated method stub
		return studentRepository.findById(studentId).get();
	}

	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(int studentId) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(studentId);
	}

	@Override
	public List<Student> searchBy(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return studentRepository.findByFirstNameContainsAndLastNameContainsAllIgnoreCase(firstName, lastName);
	}

}
