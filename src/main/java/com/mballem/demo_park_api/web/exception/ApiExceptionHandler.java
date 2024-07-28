package com.mballem.demo_park_api.web.exception;

import com.mballem.demo_park_api.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> UserNotFoundException(EntityNotFoundException exception,
                                                              HttpServletRequest request) {
        String message = messageSource.getMessage("exception.entityNotFoundException",
                new Object[]{exception.getRecurso(), exception.getCodigo()}, request.getLocale());

        log.error("ApiError - ", exception.getCause());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(ReciboNotFindException.class)
    public ResponseEntity<ErrorMessage> ReciboNotFindException(ReciboNotFindException exception,
                                                               HttpServletRequest request) {
        String message = messageSource.getMessage("exception.ReciboNotFindException",
                new Object[]{exception.getRecurso(), exception.getRecibo()}, request.getLocale());

        log.error("ApiError - ", exception.getCause());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(CodigoUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> uniqueViolationException(CodigoUniqueViolationException exception,
                                                                 HttpServletRequest request) {
        log.error("ApiError - ", exception.getCause());
        String message = messageSource.getMessage("exception.CodigoUniqueViolationException",
                new Object[]{exception.getRecurso(), exception.getCodigo()}, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, message));
    }

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> UsernameUniqueViolationException(UsernameUniqueViolationException exception,
                                                                         HttpServletRequest request) {
        log.error("ApiError - ", exception.getCause());
        String message = messageSource.getMessage("exception.CodigoUniqueViolationException",
                new Object[]{exception.getRecurso(), exception.getUsuario()}, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                        HttpServletRequest request,
                                                                        BindingResult result) {

        log.error("ApiError - ", exception.getCause());
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY,
                        messageSource.getMessage("message" +
                                ".invalid.field", null, request.getLocale()), result,
                        messageSource));
    }

    @ExceptionHandler(CpfUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> CpfUniqueViolationException(CpfUniqueViolationException exception,
                                                                    HttpServletRequest request) {

        String message = messageSource.getMessage("exception.CpfUniqueViolationException",
                new Object[]{exception.getRecurso(), exception.getCpf()}, request.getLocale());
        log.error("ApiError - ", exception.getCause());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, message));
    }

    @ExceptionHandler(NenhumaVagaDisponivelException.class)
    public ResponseEntity<ErrorMessage> NenhumaVagaDisponivelException(RuntimeException exception,
                                                                       HttpServletRequest request) {
        String message = messageSource.getMessage("exception.NenhumaVagaDisponivelException",
                null, request.getLocale());
        log.error("ApiError - ", exception.getCause());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> PasswordInvalidException(PasswordInvalidException exception,
                                                                 HttpServletRequest request) {

        log.error("ApiError - ", exception.getCause());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> AccessDeniedException(AccessDeniedException exception,
                                                              HttpServletRequest request) {

        log.error("ApiError - ", exception.getCause());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.FORBIDDEN, exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerErrorException(Exception exception, HttpServletRequest request) {
        ErrorMessage error = new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        log.error("Internal Server Error {} {} ", error, exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}
