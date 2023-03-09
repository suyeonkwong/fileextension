package flow.fileextension.service;

import flow.fileextension.dto.fileextension.FileExtensionResponse;
import flow.fileextension.entity.FileExtension;
import flow.fileextension.repository.FileExtensionRepository;
import flow.fileextension.util.constant.FileExtensionType;
import flow.fileextension.util.constant.FileExtensionVisibility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileExtensionService {

    private final FileExtensionRepository fileExtensionRepository;

    @Transactional(readOnly = true)
    public List<FileExtensionResponse> getFileExtensions(FileExtensionType fileExtensionType) {
        return fileExtensionRepository.findByFileExtensionType(fileExtensionType).stream().map(extensions -> (new FileExtensionResponse
                (extensions.getFileExtensionSeq(), extensions.getFileExtensionName(), extensions.getFileExtensionType(),
                        extensions.getFileExtensionVisibility()))).collect(Collectors.toList());
    }

    @Transactional
    public void updateFileExtensionType(long fileExtensionSeq) {
        final FileExtension fileExtension = fileExtensionRepository.findById(fileExtensionSeq).orElseThrow(NoSuchElementException::new);
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
            throw new NoSuchElementException();
        }

        //사이즈 200 넘기면 예외
        final Long size = fileExtensionRepository.countByFileExtensionTypeAndFileExtensionVisibility(FileExtensionType.CUSTOM, FileExtensionVisibility.SHOW);
        if (size >= 200) {
            throw new NoSuchElementException();
        }

        final FileExtension fileExtension = fileExtensionRepository.save(FileExtension.builder().fileExtensionName(fileExtensionName).build());
        return new FileExtensionResponse(fileExtension.getFileExtensionSeq(), fileExtension.getFileExtensionName(), fileExtension.getFileExtensionType(), fileExtension.getFileExtensionVisibility());
    }
}
