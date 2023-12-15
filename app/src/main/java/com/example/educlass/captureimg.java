package com.example.educlass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class captureimg extends AppCompatActivity {
    ImageView imageView;
    Button btOpen,saveimage;
Bitmap mBitmap;
String   currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captureimg);
        imageView=findViewById(R.id.image_view);
        btOpen=findViewById(R.id.bt_open);
       saveimage=findViewById(R.id.savegallery);


//requesting camera permeission
        if(ContextCompat.checkSelfPermission(captureimg.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(captureimg.this,new String[]{
                    Manifest.permission.CAMERA
            },100);


        }
       /* ActivityCompat.requestPermissions(captureimg.this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        },1);
        ActivityCompat.requestPermissions(captureimg.this,new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        },1);

*/
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open cam
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
       
       saveimage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                //saveToGallery();
               saveImage(mBitmap);

           }
       });
    }

     void saveImage(Bitmap bitmap) {
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            ContentValues values = contentValues();
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + getString(R.string.app_name));
            values.put(MediaStore.Images.Media.IS_PENDING, true);

            Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (uri != null) {
                try {
                    saveImageToStream(bitmap, this.getContentResolver().openOutputStream(uri));
                    values.put(MediaStore.Images.Media.IS_PENDING, false);
                    this.getContentResolver().update(uri, values, null, null);
                    Toast.makeText(captureimg.this, "Image saved", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } else {
            File directory = new File(Environment.getExternalStorageDirectory().toString() + '/' + getString(R.string.app_name));

            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fileName = System.currentTimeMillis() + ".png";
            File file = new File(directory, fileName);
            try {
                saveImageToStream(bitmap, new FileOutputStream(file));
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Toast.makeText(captureimg.this, "Image saved", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private ContentValues contentValues() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        }
        return values;
    }

    private void saveImageToStream(Bitmap bitmap, OutputStream outputStream) {
        if (outputStream != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void saveToGallery() {


        FileOutputStream outputStream=null;
        File file= Environment.getExternalStorageDirectory();
        File dir=new File(file.getAbsolutePath()+"/Mypics");
        dir.mkdir();
        String filename=String.format("%d.png",System.currentTimeMillis());
        File outFile=new File(dir,filename);
        try {
            outputStream=new FileOutputStream(outFile);

        }catch (Exception e){
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        try{
          outputStream.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            outputStream.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
   if(requestCode==100){
       //get img captured
       mBitmap=(Bitmap) data.getExtras().get("data");
       imageView.setImageBitmap(mBitmap);

   }

    }
}