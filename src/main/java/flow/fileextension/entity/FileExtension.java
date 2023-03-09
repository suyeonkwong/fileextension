package flow.fileextension.entity;

import flow.fileextension.util.constant.FileExtensionType;
import flow.fileextension.util.constant.FileExtensionVisibility;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileExtension extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileExtensionSeq;

    private String fileExtensionName;

    @Enumerated(EnumType.STRING)
    private FileExtensionType fileExtensionType;

    @Setter
    @Enumerated(EnumType.STRING)
    private FileExtensionVisibility fileExtensionVisibility;

    @Builder
    public FileExtension(String fileExtensionName) {
        this.fileExtensionName = fileExtensionName;
        this.fileExtensionType = FileExtensionType.CUSTOM;
        this.fileExtensionVisibility = FileExtensionVisibility.SHOW;
    }
}
