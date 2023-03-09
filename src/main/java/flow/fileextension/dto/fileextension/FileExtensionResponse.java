package flow.fileextension.dto.fileextension;

import flow.fileextension.util.constant.FileExtensionType;
import flow.fileextension.util.constant.FileExtensionVisibility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FileExtensionResponse {

    private final long fileExtensionSeq;
    private final String fileExtensionName;
    private final FileExtensionType fileExtensionType;
    private final FileExtensionVisibility fileExtensionVisibility;
}
