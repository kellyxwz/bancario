package com.sistema.bancario.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class StandError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/DD/mm'T'HH:mm:ss'z'", timezone = "GMT")
    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;


}
