package isga.artiweb.tourismapp.exc_handler;

import isga.artiweb.tourismapp.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        System.out.println("in global handler : method argument invalid");
        Map<String, String> errorMap = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
        System.out.println("in global handler : method argument type mismatch" + ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ApiResponse(
                "Type mismatch for param : " + ex.getName() + " Details : " +ex.getMostSpecificCause()
                        .getMessage(),false));
    }
}
