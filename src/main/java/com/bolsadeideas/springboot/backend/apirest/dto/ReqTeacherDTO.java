package com.bolsadeideas.springboot.backend.apirest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReqTeacherDTO implements Serializable {

    private static final long serialVersionUID = 985794692079072415L;

    @ApiModelProperty(example = "David", required = true, value = "Nombre del profesor")
    private String name;
    @ApiModelProperty(example = "Zuluaga", required = true, value = "Apellido del profesor")
    private String lastname;
    @ApiModelProperty(example = "1234567890", required = true, value = "Identifiaciópn del profesor")
    private Long identification;
    @ApiModelProperty(example = "david.zuluaga@net.com", required = true, value = "Correo del profesor")
    private String email;
    @ApiModelProperty(example = "3002288700", required = true, value = "Celular del profesor")
    private Long mobile;
    @ApiModelProperty(example = "david", required = true, value = "Nombre de usuario")
    private String user;
    @ApiModelProperty(example = "123456", required = true, value = "Contraseña de usuario")
    private String password;
    @ApiModelProperty(example = "ROLE_ADMIN", required = true, value = "Perfil del usuario")
    private String role;


}
