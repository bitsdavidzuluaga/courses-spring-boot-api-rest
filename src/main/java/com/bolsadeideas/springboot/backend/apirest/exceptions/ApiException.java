package com.bolsadeideas.springboot.backend.apirest.exceptions;

import com.bolsadeideas.springboot.backend.apirest.Enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException extends Throwable {

    private ErrorEnum error;
    private String message;

    private static final long serialVersionUID = 1L;

}
