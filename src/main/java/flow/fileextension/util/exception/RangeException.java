package flow.fileextension.util.exception;

import flow.fileextension.util.constant.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RangeException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String value;
}
