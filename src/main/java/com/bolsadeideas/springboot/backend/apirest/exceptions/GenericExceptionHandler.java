package com.bolsadeideas.springboot.backend.apirest.exceptions;

import com.bolsadeideas.springboot.backend.apirest.Enums.ErrorEnum;
import com.bolsadeideas.springboot.backend.apirest.dto.GeneralResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GenericExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponseDTO> handleAnyException(Exception ex) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        response.setMessage(ErrorEnum.GENERAL_ERROR.getMessage()
                + ex.getMessage() + " "
                + ex.getLocalizedMessage());
        response.setCode(ErrorEnum.GENERAL_ERROR.getCode());
        return new ResponseEntity(response, ErrorEnum.GENERAL_ERROR.getHttpStatus());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<GeneralResponseDTO> handleApiException(ApiException ex) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        response.setCode(ex.getError().getCode());
        response.setMessage(ex.getError().getMessage() + " "
                + ex.getMessage());
        return new ResponseEntity(response, ex.getError().getHttpStatus());
    }

}
