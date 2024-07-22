package org.bambacompany.springboot.exceptions;

import org.bambacompany.springboot.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public static final Logger log = LoggerFactory.getLogger(ResourceNotFoundException.class);
    private String resourceName;
    private String filedName;
    private Long fieldValue;
    private String cause;

    public ResourceNotFoundException(String resourceName, String filedName, Long fieldValue, String cause) {
        super(String.format("%s not found with %s : '%s'", resourceName, filedName, fieldValue));
        log.debug(String.format("%s not found with %s : '%s'", resourceName, filedName, fieldValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
        this.cause = cause;
    }
}
