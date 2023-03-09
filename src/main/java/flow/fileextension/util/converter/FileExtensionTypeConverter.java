package flow.fileextension.util.converter;

import flow.fileextension.util.constant.FileExtensionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

@Converter(autoApply = true)
public class FileExtensionTypeConverter implements AttributeConverter<FileExtensionType, String> {

    @Override
    public String convertToDatabaseColumn(FileExtensionType attribute) {
        return attribute.name();
    }

    @Override
    public FileExtensionType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(FileExtensionType.class).stream()
                .filter(c -> c.name().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
