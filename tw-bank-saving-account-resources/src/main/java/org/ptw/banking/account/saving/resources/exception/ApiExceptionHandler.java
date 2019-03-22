package org.ptw.banking.account.saving.resources.exception;

import java.util.Date;

import org.ptw.banking.account.saving.persistance.exception.ItemAlreadyExistException;
import org.ptw.banking.account.saving.persistance.exception.ItemNotFoundException;
import org.ptw.banking.account.saving.service.api.SavingAccountApi;
import org.ptw.banking.account.saving.service.exception.ApplicationException;
import org.ptw.banking.account.saving.service.exception.DataIntegrityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Exception handler for Saving Account API.
 * @author pawan
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler implements SavingAccountApi {

	@ExceptionHandler({ApplicationException.class, Exception.class, DataIntegrityException.class})
	  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    System.out.println(ex);
	    ex.printStackTrace();
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	  @ExceptionHandler(ItemNotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleItemNotFoundException(ItemNotFoundException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    System.out.println(ex);
	    ex.printStackTrace();
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }

	  @ExceptionHandler(ItemAlreadyExistException.class)
	  public final ResponseEntity<ExceptionResponse> handleItemAlreadyExistException(ItemAlreadyExistException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    System.out.println(ex);
	    ex.printStackTrace();
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
	  }

	}