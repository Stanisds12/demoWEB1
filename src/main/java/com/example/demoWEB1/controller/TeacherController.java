package com.example.demoWEB1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoWEB1.model.Teacher;
import com.example.demoWEB1.service.TeacherService;

@RestController
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
 // GET ALL TEACHER FROM DATABASE
	@RequestMapping("/")
	@ResponseBody
	public ModelAndView getAll() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("teachers", teacherService.listTeachers());
		return model;
	}
   // SHOW FORM TO EDIT TEACHER
	@GetMapping("/teacher/edit/{id}")
	@ResponseBody
	public ModelAndView formEdit(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		model.setViewName("editTeacher");
		if (teacherService.getTeacher(id).isPresent()) {
			model.addObject("teacher", teacherService.getTeacher(id).get() );
		}
		return model;
	}
	//EDIT TEACHER
	@PostMapping("/teacher/editTeacher")
	@ResponseBody
	public ModelAndView  editTeacher(@ModelAttribute("teacher") Teacher teacher) {
		
		Optional<Teacher> t = teacherService.getTeacher(teacher.getId());
		t.get().setId(teacher.getId());
		t.get().setName(teacher.getName());
		t.get().setExpertise(teacher.getExpertise());
		teacherService.updateTeacher(t.get(), teacher.getId());
		ModelAndView model = new ModelAndView("redirect:/");
		return model;

	}
   // INSERT TEACHER TO DATABASE
	@PostMapping("/teacher/addTeachers")
	@ResponseBody
	public ModelAndView insertTeacher(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.insertTeacher(teacher);
		ModelAndView model = new ModelAndView("redirect:/");
		model.addObject("teachers", teacherService.listTeachers());
		return model;

	}
	
	// DELETE TEACHER FROM DATABASE
	@GetMapping("/teacher/delete/{id}")
	@ResponseBody
	public ModelAndView eliminateTeacher(@PathVariable int id) {
		teacherService.deleteTeacher(id);;
		ModelAndView model = new ModelAndView("redirect:/");
		model.addObject("teachers", teacherService.listTeachers());
		return model;
	}
/**======================================================================== */
	// CRUD POR CONSOLA
	@GetMapping("/teacher/{id}")
	public Optional<Teacher> getTeacher(@PathVariable int id) {
		Optional<Teacher> teacher = teacherService.getTeacher(id);
		return teacher;
	}

	@GetMapping("/teacher")
	public List<Teacher> getTeachers() {
		List<Teacher> teacher = teacherService.listTeachers();
		return teacher;
	}

	@PostMapping("/teacher/addTeacher")
	public void addTeacher(@RequestBody Teacher teacher) {
		teacherService.insertTeacher(teacher);

	}

	@DeleteMapping("/delete/{id}")
	public List<Teacher> deleteTeacher(@PathVariable int id) {
		teacherService.deleteTeacher(id);
		return teacherService.listTeachers();
	}


}
