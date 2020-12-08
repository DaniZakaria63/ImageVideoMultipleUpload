package com.daniza.easymultipleuploadimages;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import org.json.JSONObject;

public interface MultipleUploadListener {
    UploadProgressListener onUploadListener(long bytesUploaded, long totalBytes);
    JSONObjectRequestListener UploadSuccessListener(JSONObject response);
    ANError UploadResponseError(ANError error);
    void UploadErrorListener(MultipleUploadException e);
}
