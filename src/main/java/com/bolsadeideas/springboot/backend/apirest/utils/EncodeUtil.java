package com.bolsadeideas.springboot.backend.apirest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncodeUtil {


	@Autowired
    private PasswordEncoder passwordEncoder;

    public boolean comparePass(String pass, String passBase) {
        return passwordEncoder.matches(pass, passBase);
    }

    public String encodePass(String pass) {
        return passwordEncoder.encode(pass);
    }

}
