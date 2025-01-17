package com.lcaohoanq.samplewebapplication.exceptions;

import com.lcaohoanq.samplewebapplication.components.LocalizationUtils;
import com.lcaohoanq.samplewebapplication.exceptions.base.DataAlreadyExistException;
import com.lcaohoanq.samplewebapplication.exceptions.base.DataNotFoundException;
import com.lcaohoanq.samplewebapplication.exceptions.base.DataWrongFormatException;
import com.lcaohoanq.samplewebapplication.responses.ExceptionResponse;
import com.lcaohoanq.samplewebapplication.responses.base.BaseResponse;
import com.lcaohoanq.samplewebapplication.utils.MessageKey;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler {

    private final LocalizationUtils localizationUtils;

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<Object> handleDataNotFoundException(DataNotFoundException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.data_not_found"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleNullPointerException(NullPointerException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.null_pointer"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(BiddingRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleBiddingRuleException(BiddingRuleException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("bid.exception.bidding_rule_error"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Object> handleException(Exception e) {

        log.error("Internal server error: ", e);

        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.internal_server_error"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(GenerateDataException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Object> handleGenerateDataException(GenerateDataException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.generate_data_error"))
                .reason(e.getMessage())
                .build();
    }

    // DataAlreadyExistsException
    @ExceptionHandler(DataAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleDataAlreadyExistsException(DataAlreadyExistException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.data_already_exist"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidApiPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleInvalidApiPathVariableException(InvalidApiPathVariableException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.invalid_api_path_variable"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleDeleteException(DeleteException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.delete_error"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(MalformDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleMalformDataException(MalformDataException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.malformed_data"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(MalformBehaviourException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleMalformBehaviourException(MalformBehaviourException e) {
        return ExceptionResponse.builder()
            .message(localizationUtils.getLocalizedMessage("exception.malformed_behaviour"))
            .reason(e.getMessage())
            .build();
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<Object> handleJwtAuthenticationException(JwtAuthenticationException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", ex.getMessage());
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResponse<Object> handlePermissionDeniedException(PermissionDeniedException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.permission_denied"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(KoiRevokeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleKoiRevokeException(KoiRevokeException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.koi_revoke"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handleBadCredentialsException(BadCredentialsException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("exception.bad_credentials"))
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(FeedbackResponseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse<Object>> handleFeedBackResponseException(FeedbackResponseException e) {
        BaseResponse<Object> response = ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage("feedback.exception"))
                .reason(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataWrongFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> handlePasswordWrongFormatException(DataWrongFormatException e) {
        return ExceptionResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKey.WRONG_FORMAT))
                .reason(e.getMessage())
                .build();
    }

}
