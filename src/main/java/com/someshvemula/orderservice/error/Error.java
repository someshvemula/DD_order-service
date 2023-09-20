package com.someshvemula.orderservice.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    private LocalDateTime timeStamp;
    private String message;
    private String path;
    private String statusCode;
}
