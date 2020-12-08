package com.daniza.easymultipleuploadimages;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;

public class EasyMultipleSelect {
    private final String TAG="EasyMultipleSelect";
    private static Activity mContext;
    private ArrayList<File> mFiles=new ArrayList<>();
    private ArrayList<String> mPath=new ArrayList<>();

    private final String INIT_SELECT_INTENT[]={"image/*","video/*","image/*,video/*"};
    private Intent mIntent=new Intent(Intent.ACTION_PICK);

    private void TODOS(){
        //TODO: MAKE PERMISSION
        //TODO: MAKE SELECT INTENT
    }
    public EasyMultipleSelect init(Activity context){
        EasyMultipleSelect.mContext=context;
        return this;
    }
    public EasyMultipleSelect selectMultipleImages(){
        mIntent.setType(INIT_SELECT_INTENT[0]);
        mContext.startActivityForResult(mIntent,Config.SELECT_MULTIPLE_IMAGE_UPLOAD_PERMISSION_CODE);
        return this;
    }
    public EasyMultipleSelect selectMultipleVideo(){
        mIntent.setType(INIT_SELECT_INTENT[1]);
        mContext.startActivityForResult(mIntent,Config.SELECT_MULTIPLE_VIDEO_UPLOAD_PERMISSION_CODE);
        return this;
    }
    public EasyMultipleSelect selectMultipleImageVideo(){
        mIntent.setType(INIT_SELECT_INTENT[2]);
        mContext.startActivityForResult(mIntent,Config.SELECT_MULTIPLE_IMAGE_VIDEO_UPLOAD_PERMISSION_CODE);
        return this;
    }

    public EasyMultipleSelect parseData(Intent data,MultipleSelectListener listener){
        String[] projection={MediaStore.Video.Media.DATA};
        String pathvalue="";
        if(data==null) {
            listener.onSelectErrorListener(new MultipleSelectException("Tidak ada yang dipilih"));
            return this;
        }
        Cursor cursor=mContext.getContentResolver().query(data.getData(),projection,null,null,null);
        if(cursor != null){
            int index_col= cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            pathvalue= cursor.getString(index_col);
        }
        if(pathvalue.equalsIgnoreCase("")){
            listener.onSelectErrorListener(new MultipleSelectException());
        }

        mPath.add(pathvalue);
        mFiles.add(new File(pathvalue));

        listener.onSelectSuccessListener(mFiles,mPath);
        return this;
    }
}

