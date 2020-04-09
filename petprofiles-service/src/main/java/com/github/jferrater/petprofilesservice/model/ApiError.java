package com.github.jferrater.petprofilesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

    private int code;
    private String status;
    private String message;

}
