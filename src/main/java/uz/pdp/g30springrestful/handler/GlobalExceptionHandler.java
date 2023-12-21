package uz.pdp.g30springrestful.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.g30springrestful.dto.ErrorResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, List<String>> map = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            if (map.containsKey(fieldError.getField())) {
                map.get(fieldError.getField()).add(fieldError.getDefaultMessage());
            } else {
                map.put(fieldError.getField(), new ArrayList<>(){{add(fieldError.getDefaultMessage());}});
            }
        }
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation error")
                .body(map)
                .build();
    }
}
