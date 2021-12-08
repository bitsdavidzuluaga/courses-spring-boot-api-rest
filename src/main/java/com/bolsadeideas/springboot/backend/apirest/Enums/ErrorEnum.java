package com.bolsadeideas.springboot.backend.apirest.Enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    GENERAL_ERROR("100", "Ocurrión un error general al procesar su solicitud"),
    FIND_TEACHER_BY_USERNAME_ERROR("201", "Ocurrió un error al consultar un profesor por su Username"),
    FIND_ALL_TEACHERS_ERROR("202", "Ocurrió un error al consultar todos los profesores"),
    CREATE_TEACHER_ERROR("203", "Ocurrió un error al intentar crear un profesor");

    private String code;
    private String message;
    private HttpStatus httpStatus;

    private ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
