package com.example.angular_spring.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardError {

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;


}
