package com.bolsadeideas.springboot.backend.apirest.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Teacher;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ITeacherDao extends CrudRepository<Teacher, Long> {

    @Transactional( readOnly = true)
    @Query(value = "SELECT * FROM teacher t WHERE t.user = :user LIMIT 1", nativeQuery = true)
    Teacher findByUsername(@Param("user") String user);

}
