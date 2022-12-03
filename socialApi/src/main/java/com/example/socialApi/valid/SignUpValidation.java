package com.example.socialApi.valid;

import com.example.socialApi.dto.SignUpDTO;
import com.example.socialApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Log4j2
@Component
@RequiredArgsConstructor
public class SignUpValidation implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SignUpDTO signUpDTO = (SignUpDTO) object;
        if(userRepository.existsByNickname(signUpDTO.getNickname())){
            errors.rejectValue("nickname","invalid.nickname",new Object[]{signUpDTO.getNickname()},"이미 사용중인 닉네임 또는 이메일 입니다.");
        }
        if(userRepository.existsByEmail(signUpDTO.getEmail())){
            errors.rejectValue("email","invalid.email",new Object[]{signUpDTO.getEmail()},"이미 사용중인 이메일 입니다.");
        }
    }
}
