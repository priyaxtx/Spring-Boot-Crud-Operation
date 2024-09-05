package com.example.demo.exception;

import com.example.demo.model.User;

public class UserNotFoundExeception extends  RuntimeException{
public UserNotFoundExeception(Long id) {
    super("User not found");
}
}
