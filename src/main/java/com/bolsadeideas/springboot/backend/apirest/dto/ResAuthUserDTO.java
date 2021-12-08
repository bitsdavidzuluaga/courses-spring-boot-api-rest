package com.bolsadeideas.springboot.backend.apirest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResAuthUserDTO {

    private String token;
    private String username;
    private Long id;

}
