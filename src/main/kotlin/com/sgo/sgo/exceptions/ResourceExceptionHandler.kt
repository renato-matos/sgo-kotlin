package com.sgo.sgo.exceptions

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ResourceExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest) : ResponseEntity<StandardError> {
//        val error = "Invalid arguments"
//        val status: HttpStatus = HttpStatus.BAD_REQUEST
//        val se = StandardError(Instant.now(), status.value(), error, e.message, request.requestURI)
//        return ResponseEntity.status(status).body(se)
//    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException): ValidationError {
        val violations = exception.bindingResult.allErrors
                .mapNotNull { error ->
                    when (error) {
                        is FieldError -> Violation(error.field, error.defaultMessage ?: "")
                        is ObjectError -> Violation(error.objectName, error.defaultMessage ?: "")
                        else -> null
                    }
                }
                .toList()
        return ValidationError(violations)
    }

    @ExceptionHandler(value = [MissingKotlinParameterException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingKotlinParameter(exception: MissingKotlinParameterException): ValidationError {
        val fieldName = exception.path.joinToString(separator = ".") { it.fieldName }
        val violation = Violation(fieldName, "must not be null")
        return ValidationError(listOf(violation))
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(e: ResourceNotFoundException, request: HttpServletRequest) : ResponseEntity<StandardError> {
        val error = "Resource Not Found"
        val status: HttpStatus = HttpStatus.NOT_FOUND
        val se = e.message?.let { StandardError(Instant.now(), status.value(), error, it, request.requestURI) }
        return ResponseEntity.status(status).body(se)
    }
}