package flow.fileextension.entity;

import flow.fileextension.util.constant.FileExtensionType;
import flow.fileextension.util.constant.FileExtensionVisibility;

import javax.persistence.*;

@Entity
public class FileExtension extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileExtensionSeq;

    private String fileExtensionName;

    @Enumerated(EnumType.STRING)
    private FileExtensionType fileExtensionType;

    @Enumerated(EnumType.STRING)
    private FileExtensionVisibility fileExtensionVisibility;
}
