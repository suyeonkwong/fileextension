package flow.fileextension.util.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_VALUE(HttpStatus.BAD_REQUEST, "존재하지 않는 값입니다."),
    DUPLICATE_FILE_EXTENSION_NAME(HttpStatus.BAD_REQUEST, "이미 저장된 확장자입니다."),

    FAILED_ADD_CUSTOM_FILE_EXTENSION(HttpStatus.INTERNAL_SERVER_ERROR, "커스텀 파일 확장자 추가에 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
