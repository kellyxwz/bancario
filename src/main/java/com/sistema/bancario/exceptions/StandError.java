package com.sistema.bancario.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class StandError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/DD/mm'T'HH:mm:ss'z'", timezone = "GMT")
    private LocalDate timeStamp;
    private String message;
    private Integer status;
    private String error;



}
