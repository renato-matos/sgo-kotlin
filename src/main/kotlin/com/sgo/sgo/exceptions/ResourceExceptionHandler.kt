package com.sgo.sgo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest) : ResponseEntity<StandardError> {
        val error = "Invalid arguments"
        val status: HttpStatus = HttpStatus.BAD_REQUEST
        val se = StandardError(Instant.now(), status.value(), error, e.message, request.requestURI)
        return ResponseEntity.status(status).body(se)
    }
}