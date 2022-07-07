package com.wemabank.vendorapi.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ResponseHelper {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timeStamp = new Date();

    private Boolean status;

    private String message;

    private Object data;

    public ResponseHelper(Boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
