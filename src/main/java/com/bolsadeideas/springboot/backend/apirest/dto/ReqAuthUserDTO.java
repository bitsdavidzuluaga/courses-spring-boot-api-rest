package com.bolsadeideas.springboot.backend.apirest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqAuthUserDTO {

    private String user;
    private String password;

}
