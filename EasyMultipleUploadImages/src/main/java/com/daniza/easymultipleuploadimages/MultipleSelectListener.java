package com.daniza.easymultipleuploadimages;

import android.net.Uri;

import java.io.File;
import java.util.ArrayList;

public interface MultipleSelectListener{
    void onSelectSuccessListener(ArrayList<File> mFiles, ArrayList<String> mPath);
    void onSelectErrorListener(MultipleSelectException MSException);
}
