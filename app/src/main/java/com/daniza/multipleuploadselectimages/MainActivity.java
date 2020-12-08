package com.daniza.multipleuploadselectimages;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.daniza.easymultipleuploadimages.Config;
import com.daniza.easymultipleuploadimages.EasyMultipleSelect;
import com.daniza.easymultipleuploadimages.EasyMultipleUpload;
import com.daniza.easymultipleuploadimages.MultipleSelectException;
import com.daniza.easymultipleuploadimages.MultipleSelectListener;
import com.daniza.easymultipleuploadimages.MultipleUploadException;
import com.daniza.easymultipleuploadimages.MultipleUploadListener;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private final String URL="http://192.168.1.12/gma5/index.php";
    private EasyMultipleSelect mSelect;
    private EasyMultipleUpload.Builder mBuilder;
    private EasyMultipleUpload mUpload;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvCLick=findViewById(R.id.tvClick);
        tvCLick.setText("Click On Me!");
        tvCLick.setOnClickListener(new View.OnClickListener() {
            void bind(){

            }
            @Override
            public void onClick(View view) {
                mSelect=new EasyMultipleSelect().init(MainActivity.this)
                        .selectMultipleImages();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mSelect.parseData(data, new MultipleSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSelectSuccessListener(ArrayList<File> mFiles, ArrayList<String> mPath) {

                HashMap<String, String> body = new HashMap<>();
                body.put("action", "TESTING");
                body.put("token", "LOGIN_TOKEN");

                mBuilder=new EasyMultipleUpload.Builder().init(URL)
                        .setCustomOkHttpClient(Config.getService())
                        .setBodyParam(body)
                        .setFiles(mFiles);

                mUpload= new EasyMultipleUpload(MainActivity.this)
                        .setBuilder(mBuilder)
                        .execute(new MultipleUploadListener() {
                            @Override
                            public JSONObjectRequestListener UploadSuccessListener(JSONObject response) {
                                Log.d("DANIZA", "UploadSuccessListener: "+response.toString());
                                Toast.makeText(MainActivity.this, "INI RESPONSE SUCCESS", Toast.LENGTH_SHORT).show();
                                return null;
                            }

                            @Override
                            public UploadProgressListener onUploadListener(long bytesUploaded, long totalBytes) {
                                Toast.makeText(MainActivity.this, "INI RESPONSE PROGRESS", Toast.LENGTH_SHORT).show();
                                return null;
                            }

                            @Override
                            public void UploadErrorListener(MultipleUploadException e) {
                                Toast.makeText(MainActivity.this, "INI UPLOAD ERROR", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public ANError UploadResponseError(ANError error) {
                                Toast.makeText(MainActivity.this, "INI REPONSE ERROR: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                                return null;
                            }
                        });
            }

            @Override
            public void onSelectErrorListener(MultipleSelectException MSException) {
                Toast.makeText(MainActivity.this, "Error: "+MSException.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}