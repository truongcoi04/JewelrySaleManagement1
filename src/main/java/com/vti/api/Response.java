package com.vti.api;

import lombok.Data;

@Data
public class Response {

    private Long status;

    private boolean error;

    private String message;

    private Object data;


}
