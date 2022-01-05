package com.example.demoWEB1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoWEB1.dao.TeacherDao;
import com.example.demoWEB1.model.Teacher;

@Service
public class TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	
	public Optional<Teacher> getTeacher(final int id) {
		return teacherDao.get(id);
	}
	
	public List<Teacher> listTeachers() {
		return teacherDao.list();
	}
	
	public void insertTeacher(Teacher t) {
		teacherDao.create(t);
	}
	public void updateTeacher(Teacher t, int id) {
		teacherDao.update(t, id);
	}
	
	public void deleteTeacher(int id) {
		teacherDao.delete(id);
	}

}
