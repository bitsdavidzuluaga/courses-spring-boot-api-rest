package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bolsadeideas.springboot.backend.apirest.Enums.ErrorEnum;
import com.bolsadeideas.springboot.backend.apirest.dto.GeneralResponseDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ReqAuthUserDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ReqTeacherDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ResAuthUserDTO;
import com.bolsadeideas.springboot.backend.apirest.exceptions.ApiException;
import com.bolsadeideas.springboot.backend.apirest.services.TeacherService;
import com.bolsadeideas.springboot.backend.apirest.utils.ConstantsUtil;
import com.bolsadeideas.springboot.backend.apirest.utils.MapperUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Teacher;

@RestController
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Accion exitosa"),
		@ApiResponse(code = 500, message = "Internal Server Error")
})
@Api(value="TeacherRestController")
@RequestMapping("/api")
public class TeacherRestController {

	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("/teacher")
	public ResponseEntity<GeneralResponseDTO> index() throws ApiException {

		GeneralResponseDTO<List<Teacher>> response = GeneralResponseDTO.<List<Teacher>>builder().build();
		List<Teacher> teachers = null;

		try {
			teachers = teacherService.findAll();
		} catch (DataAccessException e) {
			throw new ApiException(ErrorEnum.FIND_ALL_TEACHERS_ERROR, e.getMessage());
		}

		if (ObjectUtils.isEmpty(teachers)) {
			response.setCode(ConstantsUtil.RESPONSE_SUCCESSFUL_CODE);
			response.setMessage(ConstantsUtil.NOT_FOUND_TEACHERS_MESSAGE);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setBody(teachers);
		response.setCode(ConstantsUtil.RESPONSE_SUCCESSFUL_CODE);
		response.setMessage(ConstantsUtil.RESPONSE_SUCCESSFUL_MESSAGE);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	

	@GetMapping("/teacher/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Teacher teacher = null;
		Map<String, Object> response = new HashMap<>();
		
		try {			
			teacher = teacherService.findById(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta en la base ded atos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if (teacher == null) {
			response.put("Mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!!")));
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(teacher, HttpStatus.OK);
	}


	@ApiOperation(value = "Creación de profesores", notes = "Crea un usuario profesor")
	@PostMapping(path= "/teacher/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponseDTO<Teacher>> createTeacher(@RequestBody ReqTeacherDTO reqTeacherDTO) throws ApiException {
		GeneralResponseDTO<Teacher> response = GeneralResponseDTO.<Teacher>builder().build();
		Teacher teacher = null;

		try {
			// TODO: Validar existencia de un profesor
			teacher = teacherService.save(MapperUtil.dtoToTeacher(reqTeacherDTO));
		} catch (DataAccessException e) {
			throw new ApiException(ErrorEnum.CREATE_TEACHER_ERROR, e.getMessage());
		}

		response.setBody(teacher);
		response.setCode(ConstantsUtil.RESPONSE_SUCCESSFUL_CODE);
		response.setMessage(ConstantsUtil.RESPONSE_SUCCESSFUL_MESSAGE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
