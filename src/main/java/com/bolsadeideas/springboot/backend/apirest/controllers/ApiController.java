package com.bolsadeideas.springboot.backend.apirest.controllers;

import com.bolsadeideas.springboot.backend.apirest.business.ControllerBusiness;
import com.bolsadeideas.springboot.backend.apirest.dto.GeneralResponseDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ReqAuthUserDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ResAuthUserDTO;
import com.bolsadeideas.springboot.backend.apirest.exceptions.ApiException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class ApiController {

    @Autowired
    private ControllerBusiness controllerBusiness;

    @ApiOperation(value = "Autenticación de profesores", notes = "Retorna el token de autenticación")
    @PostMapping(path= "/teacher/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralResponseDTO<ResAuthUserDTO>> authUsers(@RequestBody ReqAuthUserDTO reqAuthUserDTO) throws ApiException {
        return new ResponseEntity<>(controllerBusiness.authUsers(reqAuthUserDTO), HttpStatus.OK);
    }


}
