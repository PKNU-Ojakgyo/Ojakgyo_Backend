package Ojakgyo.com.example.Ojakgyo.controller;

import Ojakgyo.com.example.Ojakgyo.dto.UserSignupDto;
import Ojakgyo.com.example.Ojakgyo.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign-up")
public class UserSignupController {

    private final SignupService signupService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    /*
    가능하다면 sms 인증 추가 + 비밀번호 암호화 추가
    priate final SmsService smsService
    * */

    @PostMapping
    public Object register(@RequestBody UserSignupDto request) throws IOException {

        try {
            signupService.checkPhone(request.getPhone());
            request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            signupService.signup(request);
            return Map.of("result", "성공");
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/email")
    public Object checkEmail(@RequestParam String email) {
        signupService.checkDuplicateEmail(email);
        return Map.of("result", "중복되지 않은 이메일입니다.");
    }

}
