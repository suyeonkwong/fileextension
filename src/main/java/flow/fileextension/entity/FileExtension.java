package flow.fileextension.entity;

import flow.fileextension.util.constant.FileExtensionType;
import flow.fileextension.util.constant.FileExtensionVisibility;

import javax.persistence.*;

@Entity
public class FileExtension extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileExtensionSeq;

    @Enumerated(EnumType.STRING)
    private FileExtensionType fileExtensionName;

    @Enumerated(EnumType.STRING)
    private FileExtensionVisibility fileExtensionVisibility;
}
