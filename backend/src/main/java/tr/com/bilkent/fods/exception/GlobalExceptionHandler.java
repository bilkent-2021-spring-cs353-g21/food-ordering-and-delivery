package tr.com.bilkent.fods.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;

import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        logger.warn("Validation failed", ex);
        BindingResult result = ex.getBindingResult();
        CustomHTTPResponse bodyOfResponse = new CustomHTTPResponse(result.getAllErrors(),
                "Invalid " + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        logger.warn("Method argument invalid", ex);
        BindingResult result = ex.getBindingResult();
        CustomHTTPResponse bodyOfResponse = new CustomHTTPResponse(result.getAllErrors(),
                "Invalid " + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<Object> handleUsernameExistsException(UsernameExistsException ex) {
        log.info("Existing username registration request: {}", ex.getMessage());
        CustomHTTPResponse bodyOfResponse = new CustomHTTPResponse("", "Username already exists");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_CONFLICT);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(Exception ex) {
        logger.error("Server exception", ex);
        CustomHTTPResponse bodyOfResponse = new CustomHTTPResponse("Internal Error");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_INTERNAL_SERVER_ERROR);
    }

}
