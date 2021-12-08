package com.bolsadeideas.springboot.backend.apirest.business;

import com.bolsadeideas.springboot.backend.apirest.Enums.ErrorEnum;
import com.bolsadeideas.springboot.backend.apirest.dto.GeneralResponseDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ReqAuthUserDTO;
import com.bolsadeideas.springboot.backend.apirest.dto.ResAuthUserDTO;
import com.bolsadeideas.springboot.backend.apirest.exceptions.ApiException;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Teacher;
import com.bolsadeideas.springboot.backend.apirest.services.TeacherService;
import com.bolsadeideas.springboot.backend.apirest.utils.ConstantsUtil;
import com.bolsadeideas.springboot.backend.apirest.utils.EncodeUtil;
import com.bolsadeideas.springboot.backend.apirest.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ControllerBusiness {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EncodeUtil encodeUtil;

    @Autowired
    private JwtUtil jwtUtil;

    public GeneralResponseDTO<ResAuthUserDTO> authUsers(ReqAuthUserDTO reqAuthUserDTO) throws ApiException {
        GeneralResponseDTO<ResAuthUserDTO> response = GeneralResponseDTO.<ResAuthUserDTO>builder().build();
        Teacher teacher = null;
        Map<String, Object> claims = new HashMap<>();

        try {
            teacher = teacherService.findByUsername(reqAuthUserDTO.getUser());
        } catch (DataAccessException e) {
            throw new ApiException(ErrorEnum.FIND_TEACHER_BY_USERNAME_ERROR, e.getMessage());
        }

        if (teacher != null ) {
            if(encodeUtil.comparePass(reqAuthUserDTO.getPassword(), teacher.getPassword())) {
                claims.put("name", teacher.getName() + teacher.getLastname());
                String token = jwtUtil.createJwtToken(
                        teacher.getUser(),
                        teacher.getRole(),
                        claims);

                response.setBody(ResAuthUserDTO.builder()
                        .token(token)
                        .username(teacher.getName() + " " + teacher.getLastname())
                        .id(teacher.getId())
                                .build());

                response.setCode(ConstantsUtil.RESPONSE_SUCCESSFUL_CODE);
                response.setMessage(ConstantsUtil.RESPONSE_SUCCESSFUL_MESSAGE);

            } else {
                response.setCode(ConstantsUtil.INCORRECT_CREDENTIALS_CODE);
                response.setMessage(ConstantsUtil.INCORRECT_CREDENTIALS_MESSAGE);
            }
        }

        return response;

    }

}
