package flow.fileextension.util;

import flow.fileextension.dto.error.ErrorResponse;
import flow.fileextension.util.exception.DuplicateException;
import flow.fileextension.util.exception.NonExistentException;
import flow.fileextension.util.exception.RangeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NonExistentException.class)
    public ResponseEntity<ErrorResponse> nonExistentExceptionHandler(final NonExistentException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(new ErrorResponse(e.getErrorCode(), e.getValue()));
    }

    @ExceptionHandler(RangeException.class)
    public ResponseEntity<ErrorResponse> rangeExceptionHandler(final RangeException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(new ErrorResponse(e.getErrorCode(), e.getValue()));
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> rangeExceptionHandler(final DuplicateException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(new ErrorResponse(e.getErrorCode(), e.getValue()));
    }
}
