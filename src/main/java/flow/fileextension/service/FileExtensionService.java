package flow.fileextension.service;

import flow.fileextension.dto.fileextension.FileExtensionResponse;
import flow.fileextension.entity.FileExtension;
import flow.fileextension.repository.FileExtensionRepository;
import flow.fileextension.util.constant.ErrorCode;
import flow.fileextension.util.constant.FileExtensionType;
import flow.fileextension.util.constant.FileExtensionVisibility;
import flow.fileextension.util.exception.DuplicateException;
import flow.fileextension.util.exception.NonExistentException;
import flow.fileextension.util.exception.RangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  FileExtensionService {

    private static final int CUSTOM_MAX_SIZE = 200;
    private final FileExtensionRepository fileExtensionRepository;

    @Transactional(readOnly = true)
    public List<FileExtensionResponse> getFileExtensions(FileExtensionType fileExtensionType) {
        return fileExtensionRepository.findByFileExtensionType(fileExtensionType).stream().map(extensions -> (new FileExtensionResponse
                (extensions.getFileExtensionSeq(), extensions.getFileExtensionName(), extensions.getFileExtensionType(),
                        extensions.getFileExtensionVisibility()))).collect(Collectors.toList());
    }

    @Transactional
    public void updateFileExtensionType(long fileExtensionSeq) {
        final FileExtension fileExtension = fileExtensionRepository.findById(fileExtensionSeq).orElseThrow(() -> new NonExistentException(ErrorCode.BAD_VALUE, String.valueOf(fileExtensionSeq)));
        if (fileExtension.getFileExtensionVisibility() == FileExtensionVisibility.SHOW) {
            fileExtension.setFileExtensionVisibility(FileExtensionVisibility.HIDE);
        } else {
            fileExtension.setFileExtensionVisibility(FileExtensionVisibility.SHOW);
        }
    }

    @Transactional
    public FileExtensionResponse addCustomFileExtensionCheckSize(String fileExtensionName) {

        //이미 존재할 경우 예외
        if (fileExtensionRepository.existsByFileExtensionName(fileExtensionName)) {
            final FileExtension fileExtension = fileExtensionRepository.findByFileExtensionName(fileExtensionName).orElseThrow(() -> new NonExistentException(ErrorCode.BAD_VALUE, fileExtensionName));
            if (fileExtension.getFileExtensionVisibility() == FileExtensionVisibility.HIDE) {
                fileExtension.setFileExtensionVisibility(FileExtensionVisibility.SHOW);
                return new FileExtensionResponse(fileExtension.getFileExtensionSeq(), fileExtension.getFileExtensionName(), fileExtension.getFileExtensionType(), fileExtension.getFileExtensionVisibility());
            } else {
                throw new DuplicateException(ErrorCode.FAILED_ADD_CUSTOM_FILE_EXTENSION, fileExtensionName);
            }
        }

        //사이즈 200 넘기면 예외
        final Long size = fileExtensionRepository.countByFileExtensionTypeAndFileExtensionVisibility(FileExtensionType.CUSTOM, FileExtensionVisibility.SHOW);
        if (size >= CUSTOM_MAX_SIZE) {
            throw new RangeException(ErrorCode.DUPLICATE_FILE_EXTENSION_NAME, String.valueOf(CUSTOM_MAX_SIZE));
        }

        final FileExtension fileExtension = fileExtensionRepository.save(FileExtension.builder().fileExtensionName(fileExtensionName).build());
        return new FileExtensionResponse(fileExtension.getFileExtensionSeq(), fileExtension.getFileExtensionName(), fileExtension.getFileExtensionType(), fileExtension.getFileExtensionVisibility());
    }
}
