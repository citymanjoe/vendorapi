package com.wemabank.vendorapi.util;
import org.apache.logging.log4j.util.Strings;
import static com.wemabank.vendorapi.util.ConstantUtil.ERROR_MESSAGE;

public class ErrorResponse extends ResponseHelper {
    public ErrorResponse(String message){
        super(false, message, Strings.EMPTY);
    }

    public ErrorResponse(String message, Object data){
        super(false, message, data);
    }


    public ErrorResponse(){
        super(false, ERROR_MESSAGE, Strings.EMPTY);
    }
}
