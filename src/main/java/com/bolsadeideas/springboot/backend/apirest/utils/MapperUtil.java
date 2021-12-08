package com.bolsadeideas.springboot.backend.apirest.utils;

import com.bolsadeideas.springboot.backend.apirest.dto.ReqCourseDTO;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Course;
import org.modelmapper.ModelMapper;

import com.bolsadeideas.springboot.backend.apirest.dto.ReqTeacherDTO;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Teacher;


public class MapperUtil {

	
    public static final ModelMapper MAPPER = new ModelMapper();

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return MapperUtil.MAPPER.map(entity, outClass);
    }
    
    public static Teacher dtoToTeacher(ReqTeacherDTO req) {
    	Teacher teacher = new Teacher();
    	teacher.setName(req.getName());
    	teacher.setLastname(req.getLastname());
    	teacher.setEmail(req.getEmail());
    	teacher.setIdentification(req.getIdentification());
    	teacher.setPassword(req.getPassword());
    	teacher.setUser(req.getUser());
    	teacher.setRole(req.getRole());
    	teacher.setMobile(req.getMobile());
    	return teacher;
    }

	public static Course dtoToCourse(ReqCourseDTO req) {
		Course course = new Course();
		course.setName(req.getName());
		course.setDescription(req.getDescription());
		course.setTeacher(req.getTeacher());
		return course;
	}

}
