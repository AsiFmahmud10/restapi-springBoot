package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.example.demo.entity.ErrorDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handle(ResourceNotFoundException e, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception e) {
		ProblemDetail detail = null;

		if (e instanceof BadCredentialsException) {

			detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
			detail.setProperty("access_denied_reason", "Authentication Failure");

		} else if (e instanceof SignatureException || e instanceof MalformedJwtException) {
			detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
			detail.setProperty("access_denied_reason", "token invalid");
		} else if (e instanceof ExpiredJwtException) {
			detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
			detail.setProperty("access_denied_reason", "token expired");
		}
			return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), "Bad Req");
		
		
	};

}
