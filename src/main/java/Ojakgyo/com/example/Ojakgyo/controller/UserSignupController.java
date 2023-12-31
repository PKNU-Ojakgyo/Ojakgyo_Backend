package Ojakgyo.com.example.Ojakgyo.controller;

import Ojakgyo.com.example.Ojakgyo.dto.UserSignupDto;
import Ojakgyo.com.example.Ojakgyo.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private final SmsService smsService
    * */

    @PostMapping
    public Object register(@RequestBody @Valid UserSignupDto request) throws IOException {

        try {
            signupService.checkDuplicatePhone(request.getPhone());
            request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            signupService.signup(request);
            return Map.of("result", "성공");
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping(value = "/email", produces = "application/json; charset=UTF-8")
    public Object checkEmail(@RequestParam String email) throws IOException {
        try {
            signupService.checkDuplicateEmail(email);
            return Map.of("result", "중복되지 않은 이메일입니다.");
        } catch (Exception e) {
            throw e;
        }

    }

    @GetMapping("/phone-number")
    public Object checkPhoneNumber(@RequestParam String phoneNumber) throws IOException {
        try {
            signupService.checkDuplicatePhone(phoneNumber);
            return Map.of("result", "중복되지 않은 전화번호입니다.");
        } catch (Exception e) {
            throw e;
        }

    }

}
