package com.caneroksuz.controller;

import com.caneroksuz.dto.request.LoginRequestDto;
import com.caneroksuz.dto.request.RegisterRequestDto;
import com.caneroksuz.dto.response.UserResponseDto;
import com.caneroksuz.repository.entity.User;
import com.caneroksuz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.caneroksuz.constant.RestApiUrl.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    //@Autowired
    private final UserService userService;

    //http://localhost:8090/user/save?name=Mehmet&surname=%C3%96ks%C3%BCz&email=mehmet@gmail.com&phone=5278567&password=7824
    @GetMapping("/save")
    public ResponseEntity<?>  createUser(String name, String surname, String email, String password, String phone){
        User user = User.builder().name(name).surName(surname).email(email).password(password).phone(phone).build();

        try {
            userService.createUser(user);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Kullanıcı olusturulamadı");
        }
    }

    @GetMapping(REGISTER)
    public ResponseEntity<User> register(RegisterRequestDto dto){
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping(REGISTER)
    public ResponseEntity<User> register2(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping(FINDBYID+"/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){

        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<User> findById2(@RequestParam(required = false, defaultValue = "1") Long id){

        if (id==null){
            throw new RuntimeException("id null");
        }

        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(userService.login(dto));
    }

    @GetMapping("/orderbyname")

    public ResponseEntity<List<User>> findAllByOrderByName(){
        return ResponseEntity.ok(userService.findAllByOrderByName());
    }

    @GetMapping("/containingbyname")
    public ResponseEntity<List<UserResponseDto>> findAllByNameContainingIgnoreCase(String value){
        return ResponseEntity.ok(userService.findAllByNameContainingIgnoreCase(value));
    }

    @GetMapping("/containingbyemail")
    public ResponseEntity<List<User>> findAllByEmailContainingIgnoreCase(String value){
        return ResponseEntity.ok(userService.findAllByEmailContainingIgnoreCase(value));
    }

    @GetMapping("/email_ending_with")
    public ResponseEntity<List<User>> findAllByEmailEndingWith(String value){
        return ResponseEntity.ok(userService.findAllByEmailEndingWith(value));
    }

    @GetMapping("/lt_password")
    public ResponseEntity<List<User>> passwordLongerThan(int value){
        return ResponseEntity.ok(userService.passwordLongerThan(value));
    }

    @GetMapping("/lt_password2")
    public ResponseEntity<List<User>> passwordLongerThan2(int value){
        return ResponseEntity.ok(userService.passwordLongerThan2(value));
    }

    @GetMapping("/lt_password3")
    public ResponseEntity<List<User>> passwordLongerThan3(int value){
        return ResponseEntity.ok(userService.passwordLongerThan3(value));
    }
}
