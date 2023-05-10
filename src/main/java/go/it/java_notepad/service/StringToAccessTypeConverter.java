package go.it.java_notepad.service;

import go.it.java_notepad.entity.AccessType;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToAccessTypeConverter implements Converter<String, AccessType> {

    @Override
    public AccessType convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        return EnumUtils.getEnum(AccessType.class, source.toUpperCase());
    }
}
