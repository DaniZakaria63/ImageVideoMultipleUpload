package com.daniza.easymultipleuploadimages;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import okhttp3.OkHttpClient;

public class EasyMultipleUpload{
    private ArrayList<File> mFileList=new ArrayList<>();
    private static Activity mContext;
    private ANRequest.MultiPartBuilder mMultipartRequestBuilder;
    private Builder mBuilder;

    private void TODOS(){
        //TODO: CHECK PERMISSON INTERNET
        //TODO: UPLOAD IT USING FAN
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public EasyMultipleUpload(Activity context){
        this(new Builder());
        this.mContext=context;
        checkPermission(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    EasyMultipleUpload(Builder builder){
        this.mBuilder=builder;
    }

    public EasyMultipleUpload setBuilder(Builder builder){
        this.mBuilder=builder;
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkPermission(final Activity context){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            context.requestPermissions(new String[]{Manifest.permission.INTERNET},0);
            return false;
        }else{
            return true;
        }
    }

    public EasyMultipleUpload execute(MultipleUploadListener listener){
        try{
            if(mFileList.size()<0) throw new MultipleUploadException(MultipleUploadException.NO_FILE_SELECTED_ERROR_CODE,"Tidak ada File yang akan dikirim");
            if(mBuilder.mURL.equalsIgnoreCase("")) throw new MultipleUploadException(MultipleUploadException.NO_URL_ADDED_CODE,"Tidak ada URL untuk dituju");

            AndroidNetworking.initialize(mContext,mBuilder.mOkHttpClient);

            mMultipartRequestBuilder=new ANRequest.MultiPartBuilder(mBuilder.mURL)
                    .setOkHttpClient(mBuilder.mOkHttpClient)
                    .addMultipartParameter(mBuilder.mBody);

            for (int i = 1; i <= mFileList.size(); i++) {
                mMultipartRequestBuilder.addMultipartFile("file_"+""+i,mFileList.get(i-1));
            }
            mMultipartRequestBuilder.setPriority(Priority.HIGH).build()
                    .setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {
                            listener.onUploadListener(bytesUploaded,totalBytes);
                        }
                    })
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            listener.UploadSuccessListener(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            listener.UploadResponseError(anError);
                        }
                    });

        }catch (MultipleUploadException e){
            listener.UploadErrorListener(e);
        }
        return this;
    }

    public static class Builder{
        private boolean permissionStatus=false;
        private String mURL="";
        private OkHttpClient mOkHttpClient;
        private ArrayList<File> mFiles=new ArrayList<>();
        private HashMap<String,String> mBody;

        public Builder(){

        }
        public Builder init(String URL){
            this.mURL=URL;
            return this;
        }

        public Builder setCustomOkHttpClient(OkHttpClient okHttpClient){
            this.mOkHttpClient=okHttpClient;
            return this;
        }

        public Builder setFiles(ArrayList<File> files){
            this.mFiles=files;
            return this;
        }

        public Builder setBodyParam(HashMap<String,String> body){
            this.mBody=body;
            return this;
        }
    }
}
