package tr.com.bilkent.fods.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        log.warn("Validation failed: {}", ex.getBindingResult());
        BindingResult result = ex.getBindingResult();
        CustomHTTPResponse<List<ObjectError>> bodyOfResponse = new CustomHTTPResponse<>(result.getAllErrors(),
                "Invalid " + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.warn("Method argument invalid: {}", ex.getBindingResult());
        BindingResult result = ex.getBindingResult();
        CustomHTTPResponse<List<ObjectError>> bodyOfResponse = new CustomHTTPResponse<>(result.getAllErrors(),
                "Invalid " + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        log.warn("Missing request parameter: {}", ex.getMessage());

        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(ex.getMessage());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        if (body != null) {
            return super.handleExceptionInternal(ex, body, headers, status, request);
        }
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(ex.getMessage());
        return super.handleExceptionInternal(ex, bodyOfResponse, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        log.info("Constraint violation: {}", ex.getMessage());
        ConstraintViolation<?> violation = ex.getConstraintViolations().iterator().next();
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Constraint violation: " + violation.getPropertyPath() + " " + violation.getMessage());
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_BAD_REQUEST);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<Object> handleUsernameExistsException(UsernameExistsException ex) {
        log.info("Existing username registration request: {}", ex.getMessage());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>("Username already exists");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_CONFLICT);
    }

    @ExceptionHandler(MealExistsException.class)
    public ResponseEntity<Object> handleMealExistsException(MealExistsException ex) {
        log.info("Meal already exists: rid = {} name = {}", ex.getRid(), ex.getName());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>("Meal already exists");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.info("Database integrity violation: {}", ex.getMessage());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>("Database integrity violation");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_BAD_REQUEST);
    }

    @ExceptionHandler(NonExistsRestaurantException.class)
    public ResponseEntity<Object> handleNonExistsRestaurantException(NonExistsRestaurantException ex) {
        log.info("Restaurant does not exist: rid = {}", ex.getRid());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Restaurant does not exist, or you don't have access to it.");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_NOT_FOUND);
    }

    @ExceptionHandler(NonExistsCommentException.class)
    public ResponseEntity<Object> handleNonExistsCommentException(NonExistsCommentException ex) {
        log.info("Comment does not exist: oid = {}", ex.getOid());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Comment does not exist, or you don't have access to it.");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_NOT_FOUND);
    }

    @ExceptionHandler(NonExistsMealException.class)
    public ResponseEntity<Object> handleNonExistsMealException(NonExistsMealException ex) {
        log.info("Meal does not exist: mealName = {} rid = {}", ex.getMealName(), ex.getRid());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Meal does not exist, or you don't have access to it.");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_NOT_FOUND);
    }

    @ExceptionHandler(NonExistsOrderException.class)
    public ResponseEntity<Object> handleNonExistsOrderException(NonExistsOrderException ex) {
        log.info("Order does not exist: oid = {}", ex.getOid());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Order does not exist, or you don't have access to it.");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFromRestaurantException.class)
    public ResponseEntity<Object> handleOrderNotFromRestaurantException(OrderNotFromRestaurantException ex) {
        log.info("Order restaurant mismatch: oid = {} rid = {}", ex.getOid(), ex.getRid());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Order does not exist, or you don't have access to it.");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        log.info("Empty result set: {}", ex.getMessage());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>("No such entity found");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_NOT_FOUND);
    }

    @ExceptionHandler(CommentAlreadyRepliedException.class)
    public ResponseEntity<Object> handleCommentAlreadyRepliedException(CommentAlreadyRepliedException ex) {
        log.info("Comment has been already replied to: oid = {}", ex.getOid());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Comment already has a response.");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_BAD_REQUEST);
    }

    @ExceptionHandler(RestaurantNotManagedException.class)
    public ResponseEntity<Object> handleRestaurantNotManagedException(RestaurantNotManagedException ex) {
        log.info("Restaurant is not managed by the signed-in manager: rid = {}", ex.getRid());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Restaurant does not exist, or you don't have access to it.");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_NOT_FOUND);
    }

    @ExceptionHandler(InvalidOrderStatusException.class)
    public ResponseEntity<Object> handleInvalidOrderStatusException(InvalidOrderStatusException ex) {
        log.info("Invalid order status: {}", ex.toString());
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(
                "Invalid order status: Expected " + ex.getExpected() + ", but found " + ex.getActual());
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_BAD_REQUEST);
    }

    @ExceptionHandler(BasketContentMultipleRestaurantsException.class)
    public ResponseEntity<Object> handleBasketContentMultipleRestaurantsException(BasketContentMultipleRestaurantsException ex) {
        String msg = "Basket content cannot contain meals from different restaurants";
        log.info(msg);
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>(msg);
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(Exception ex) {
        logger.error("Server exception", ex);
        CustomHTTPResponse<Void> bodyOfResponse = new CustomHTTPResponse<>("Internal Error");
        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), SC_INTERNAL_SERVER_ERROR);
    }
}
