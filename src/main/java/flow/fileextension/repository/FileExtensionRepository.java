package flow.fileextension.repository;

import flow.fileextension.entity.FileExtension;
import flow.fileextension.util.constant.FileExtensionType;
import flow.fileextension.util.constant.FileExtensionVisibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileExtensionRepository extends JpaRepository<FileExtension, Long> {
    List<FileExtension> findByFileExtensionType(FileExtensionType fileExtensionType);
    boolean existsByFileExtensionName(String fileExtensionName);
    Long countByFileExtensionTypeAndFileExtensionVisibility(FileExtensionType fileExtensionType, FileExtensionVisibility fileExtensionVisibility);
    Optional<FileExtension> findByFileExtensionName(String fileExtensionName);
}
