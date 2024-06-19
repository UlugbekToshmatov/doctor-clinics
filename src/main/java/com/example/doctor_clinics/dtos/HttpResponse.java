package com.example.doctor_clinics.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HttpResponse {
    private String timeStamp;
    private Integer statusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;
    private String developerMessage;
    private Map<?, ?> data;
}
