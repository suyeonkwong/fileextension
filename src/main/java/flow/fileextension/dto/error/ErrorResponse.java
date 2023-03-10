package flow.fileextension.dto.error;

import flow.fileextension.util.constant.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final LocalDateTime date;
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode, String value) {
        this.date = LocalDateTime.now();
        this.status = errorCode.getHttpStatus().value();
        this.error = errorCode.getHttpStatus().name();
        this.code = errorCode.name();
        this.message = value + "는(은) " + errorCode.getMessage();
    }
}
