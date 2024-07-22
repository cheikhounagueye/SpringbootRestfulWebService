package org.bambacompany.springboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserHandleException {
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private String errorCode;
}
