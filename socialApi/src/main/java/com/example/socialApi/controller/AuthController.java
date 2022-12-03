package com.example.socialApi.controller;


import com.example.socialApi.dto.SignUpDTO;
import com.example.socialApi.service.AuthService;
import com.example.socialApi.valid.SignUpValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final SignUpValidation signUpValidation;

    @InitBinder("signUpDTO")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpValidation);
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDTO signUpDTO , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        authService.signUp(signUpDTO);
        return ResponseEntity.ok().body("success");
    }

}
