package com.daniza.easymultipleuploadimages;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class MultipleUploadException extends Exception {
    public static final int NETWORK_ERROR_CODE=3;
    public static final int NO_FILE_SELECTED_ERROR_CODE=6;
    public static final int NO_PERMISSION_ERROR_CODE=9;
    public static final int NO_URL_ADDED_CODE=36;

    private int CODE=1;

    public MultipleUploadException(int code) {
        this.setCode(code);
    }

    public MultipleUploadException(int code,String message) {
        super(message);
        this.setCode(code);
    }

    public MultipleUploadException(int code,String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public MultipleUploadException(int code,Throwable cause) {
        super(cause);
        this.setCode(code);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public MultipleUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void setCode(int code){
        this.CODE=code;
    }
    public int getCode(){
        return CODE;
    }
}
