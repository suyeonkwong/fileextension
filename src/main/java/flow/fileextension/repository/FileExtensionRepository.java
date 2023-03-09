package flow.fileextension.repository;

import flow.fileextension.entity.FileExtension;
import flow.fileextension.util.constant.FileExtensionType;
import flow.fileextension.util.constant.FileExtensionVisibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileExtensionRepository extends JpaRepository<FileExtension, Long> {
    List<FileExtension> findByFileExtensionType(FileExtensionType fileExtensionType);
    boolean existsByFileExtensionName(String fileExtensionName);
    Long countByFileExtensionTypeAndFileExtensionVisibility(FileExtensionType fileExtensionType, FileExtensionVisibility fileExtensionVisibility);
}
