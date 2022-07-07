package com.wemabank.vendorapi.util;
import static com.wemabank.vendorapi.util.ConstantUtil.SUCCESS_MESSAGE;

public class SuccessResponse extends ResponseHelper {

    public SuccessResponse(String message, Object data){
        super(true, message, data);
    }

    public SuccessResponse(Object data){
        super(true, SUCCESS_MESSAGE, data);
    }
}
