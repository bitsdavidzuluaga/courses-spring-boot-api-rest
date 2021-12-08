package com.bolsadeideas.springboot.backend.apirest.models.repository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Course;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;

public interface ICourseDao extends CrudRepository<Course, Long> {

}
