package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Course;
import com.bolsadeideas.springboot.backend.apirest.models.repository.ICourseDao;
import com.bolsadeideas.springboot.backend.apirest.utils.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Teacher;
import com.bolsadeideas.springboot.backend.apirest.models.repository.ITeacherDao;

@Service
public class TeacherService {

	@Autowired
	private ITeacherDao teacherDao;

	@Autowired
	private ICourseDao courseDao;

	@Autowired
	private EncodeUtil encodeUtil;
	

	@Transactional(readOnly = true)
	public List<Teacher> findAll() {
		return (List<Teacher>) teacherDao.findAll();
	}

	
	@Transactional(readOnly = true)
	public List<Course> findAllCourses() {
		return (List<Course>) courseDao.findAll();
	}



	@Transactional(readOnly = true)
	public Teacher findById(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.findById(id).orElse(null);
	}


	@Transactional
	public Teacher save(Teacher teacher) {
		String pass = encodeUtil.encodePass(teacher.getPassword());
		teacher.setPassword(pass);
		return teacherDao.save(teacher);
	}


	@Transactional
	public Course saveCourse(Course course) {
		return courseDao.save(course);
	}


	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		teacherDao.deleteById(id);
	}


	@Transactional
	public Teacher findByUsername(String user) {
		return teacherDao.findByUsername(user);
	}

}
