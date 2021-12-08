package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.Enums.ErrorEnum;
import com.bolsadeideas.springboot.backend.apirest.dto.GeneralResponseDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ReqCourseDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ReqTeacherDTO;
import com.bolsadeideas.springboot.backend.apirest.exceptions.ApiException;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Course;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Teacher;
import com.bolsadeideas.springboot.backend.apirest.services.TeacherService;
import com.bolsadeideas.springboot.backend.apirest.utils.ConstantsUtil;
import com.bolsadeideas.springboot.backend.apirest.utils.MapperUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Accion exitosa"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api")
public class CourseRestController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "Creación de curso", notes = "Crea un curso y lo asigna a un profesor")
    @PostMapping(path= "/course/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralResponseDTO<Course>> createCourse(@RequestBody ReqCourseDTO req) throws ApiException {
        GeneralResponseDTO<Course> response = GeneralResponseDTO.<Course>builder().build();
        Course course = null;

        try {
            course = teacherService.saveCourse(MapperUtil.dtoToCourse(req));
        } catch (DataAccessException e) {
            throw new ApiException(ErrorEnum.CREATE_TEACHER_ERROR, e.getMessage());
        }

        response.setBody(course);
        response.setCode(ConstantsUtil.RESPONSE_SUCCESSFUL_CODE);
        response.setMessage(ConstantsUtil.RESPONSE_SUCCESSFUL_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/coursesr")
	public ResponseEntity<GeneralResponseDTO> index() throws ApiException {

		GeneralResponseDTO<List<Course>> response = GeneralResponseDTO.<List<Course>>builder().build();
		List<Course> courses = null;

		try {
			courses = teacherService.findAllCourses();
		} catch (DataAccessException e) {
			throw new ApiException(ErrorEnum.FIND_ALL_TEACHERS_ERROR, e.getMessage());
		}

		if (ObjectUtils.isEmpty(courses)) {
			response.setCode(ConstantsUtil.RESPONSE_SUCCESSFUL_CODE);
			response.setMessage(ConstantsUtil.NOT_FOUND_TEACHERS_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setBody(courses);
		response.setCode(ConstantsUtil.RESPONSE_SUCCESSFUL_CODE);
		response.setMessage(ConstantsUtil.RESPONSE_SUCCESSFUL_MESSAGE);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
