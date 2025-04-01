package com.chenminty.campus3d.exception;

import lombok.Data;

@Data
public class CampusException extends RuntimeException {
    private final int code;

    public CampusException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
