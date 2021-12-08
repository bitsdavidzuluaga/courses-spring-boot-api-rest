package com.bolsadeideas.springboot.backend.apirest.dto;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Teacher;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReqCourseDTO implements Serializable {

    private static final long serialVersionUID = 985794692079072415L;

    @ApiModelProperty(example = "Spring Boot 2.6.0", required = true, value = "Nombre del curso")
    private String name;
    @ApiModelProperty(example = "Este es mi curso Java", required = true, value = "Descripción del curso")
    private String description;
    @ApiModelProperty(example = "Profesor David Zuluaga", required = true, value = "Profesor asociado al curso")
    private Teacher teacher;


}
