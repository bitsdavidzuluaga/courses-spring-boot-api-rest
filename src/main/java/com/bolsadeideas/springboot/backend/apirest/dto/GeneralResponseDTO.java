package com.bolsadeideas.springboot.backend.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponseDTO<T> {
    private T body;
    private String code;
    private String message;

}
