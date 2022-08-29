package com.vaild.test.controller;

import com.vaild.test.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @PostMapping(value = "/user")
    public String insertUser(@RequestBody @Valid UserDto userDto){
        log.info("@ param : {}", userDto);
        return "success";
    }
}
