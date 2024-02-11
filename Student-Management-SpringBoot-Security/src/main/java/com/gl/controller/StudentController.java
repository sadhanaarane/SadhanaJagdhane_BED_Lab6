package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.gl.entity.Student;
import com.gl.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ModelAndView listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		ModelAndView modelAndView = new ModelAndView("students");
		return modelAndView;
	}

	@GetMapping("/view/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ModelAndView viewStudent(@PathVariable int id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		ModelAndView modelAndView = new ModelAndView("view_student");
		return modelAndView;

	}

	@GetMapping("/new")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView createStudentForm(Model model) {

		Student student = new Student();
		model.addAttribute("student", student);
		ModelAndView modelAndView = new ModelAndView("create_student");
		return modelAndView;

	}

	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		ModelAndView modelAndView = new ModelAndView("redirect:/students/list");
		return modelAndView;

	}

	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView editStudentForm(@PathVariable int id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		ModelAndView modelAndView = new ModelAndView("edit_student");
		return modelAndView;

	}

	@PostMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView updateStudent(@PathVariable int id, @ModelAttribute("student") Student student, Model model) {

		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setCourse(student.getCourse());
		existingStudent.setCountry(student.getCountry());

		studentService.saveStudent(existingStudent);
		model.addAttribute(existingStudent);
		ModelAndView modelAndView = new ModelAndView("redirect:/students/list");
		return modelAndView;
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ModelAndView deleteStudent(@PathVariable int id) {
		studentService.deleteStudentById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/students/list");
		return modelAndView;

	}

	@GetMapping("/search")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ModelAndView searchStudent(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, Model model) {

		ModelAndView modelAndView;

		if (firstName.trim().isEmpty() && lastName.trim().isEmpty())
			return new ModelAndView("redirect:/students/list");
		else {
			List<Student> students = studentService.searchBy(firstName, lastName);

			model.addAttribute("students", students);

			modelAndView = new ModelAndView("students");

			return modelAndView;

		}
	}
}
