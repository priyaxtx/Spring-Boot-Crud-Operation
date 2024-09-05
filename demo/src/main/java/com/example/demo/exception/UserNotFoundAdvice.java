package com.example.demo.exception;
import com.example.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@ControllerAdvice
public class UserNotFoundAdvice {

    private final UserRepository userRepository;

    public UserNotFoundAdvice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundExeception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, String> exceptionsHandler(UserNotFoundExeception exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessages", exception.getMessage());
        return errorMap;

    }

}
