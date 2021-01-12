package com.adl.sampleproject.one.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private String traceId;

}
