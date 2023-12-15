package com.example.educlass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.educlass.model.imgModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadImage extends AppCompatActivity {
Button uploadimage;

ImageView imageView;
String subject;
String scls,az;
String question;
EditText sub,cls,qstn;
String a,b,c,d;
Button choose;
Bitmap bitmap;
final int REQ=1;
ProgressDialog pd;
DatabaseReference userDatabaseRef;
StorageReference reference;
String downloadUrl;
EditText roll_no;
Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            a=extras.getString("sub");
            b=extras.getString("cls");
            c=extras.getString("question");
            d=extras.getString("rno");
        }


        pd=new ProgressDialog(this);
        uploadimage=findViewById(R.id.btnupimg);
        sub=findViewById(R.id.sub);
        imageView=findViewById(R.id.galleryImageview);
        choose=findViewById(R.id.choose);
        roll_no=findViewById(R.id.edrollno);

        cls=findViewById(R.id.cls);
        qstn=findViewById(R.id.qst);
        sub.setText(a);
        cls.setText(b);
        qstn.setText(c);
        roll_no.setText(d);
/*imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent galleryIntent=new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,2);
    }
});*/
        az=roll_no.getText().toString();
        userDatabaseRef= FirebaseDatabase.getInstance().getReference().child("HWAnswer").child(sub.getText().toString()).child(roll_no.getText().toString()).child(cls.getText().toString()).child(qstn.getText().toString()).push();
        reference= FirebaseStorage.getInstance().getReference();
uploadimage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(imageUri!=null){
            final StorageReference filePath = FirebaseStorage.getInstance().getReference()
                    .child(imageUri.getPath());
            Bitmap bitmap = null;
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(),imageUri);

            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
            byte[] data = byteArrayOutputStream.toByteArray();
            UploadTask uploadTask = filePath.putBytes(data);
uploadTask.addOnFailureListener(new OnFailureListener() {
    @Override

    public void onFailure(@NonNull Exception e) {
        Toast.makeText(UploadImage.this, "Image upload failed", Toast.LENGTH_SHORT).show();
    }
});
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                if (taskSnapshot.getMetadata() != null && taskSnapshot.getMetadata().getReference() != null) {
                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
      result.addOnSuccessListener(new OnSuccessListener<Uri>() {
          @Override
          public void onSuccess(Uri uri) {
              String imageUrl = uri.toString();
              Map newImageMap = new HashMap();
            //  newImageMap.put("Roll_no",rollno.getText().toString());
              newImageMap.put("Question",qstn.getText().toString());
              newImageMap.put("imageUrl", imageUrl);

              userDatabaseRef.updateChildren(newImageMap).addOnCompleteListener(task1 -> {
                  if (task1.isSuccessful()) {
                      Toast.makeText(UploadImage.this, "Image url added to database successfully", Toast.LENGTH_SHORT).show();

                  } else {
                      Toast.makeText(UploadImage.this, task1.getException().toString(), Toast.LENGTH_SHORT).show();
                  }
              });
              finish();
          }
      });
          }
      });
        }
    }
});

choose.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent galleryIntent=new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,2);

       // openGallery();
    }


});
/*
uploadimage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(bitmap==null){
            Toast.makeText(UploadImage.this, "Pljease upload image", Toast.LENGTH_SHORT).show();
        }else {
          pd.setMessage("Uploading...");
          pd.dismiss();
          uploadimage(bitmap);
        }
    }
});*/


    }

    /*private void uploadToFirebase(Uri uri) {
        StorageReference fileRef=reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        imgModel model=new imgModel(uri.toString());
                        String modelId=userDatabaseRef.push().getKey();
                        userDatabaseRef.child(modelId).setValue(model);
                        Toast.makeText(UploadImage.this,"Upload Success.....",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                pd.setMessage("Uploading...");
               // pd.dismiss();
                //Toast.makeText(UploadImage.this,"Uploading.....",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadImage.this,"Uploading Failed...",Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    /*private String getFileExtension(Uri mUri) {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }*/
/*

    private void uploadimage(Uri uri) {
       StorageReference fileref=reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
    }
    private String getFileExtension(Uri mUri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
*/

    /*private void uploadimage() {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimag=baos.toByteArray();
        final StorageReference filePath;
        filePath=reference.child(finalimag+"jpg");
        final UploadTask uploadTask=filePath.putBytes(finalimag);
         uploadTask.addOnCompleteListener(UploadImage.this,(OnCompleteListener)(task)->{
           if(task.isSuccessful()){
               uploadTask.addOnSuccessListener((OnSuccessListener)(taskSnapshot)->{
                  filePath.getDownloadUrl().addOnSuccessListener((OnSuccessListener)(uri)->{
                      downloadUrl=String.valueOf(uri);
                      uploadData();
                  } );
               });
           }else{
               pd.dismiss();
               Toast.makeText(UploadImage.this,"Something went wrong..",Toast.LENGTH_SHORT).show();

           }
         });

    }*/




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2&&resultCode==RESULT_OK&&data!=null){
           imageUri=data.getData();
           imageView.setImageURI(imageUri);

            /* Uri uri=data.getData();
            try{
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            }catch (IOException e){
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
        */}
    }
}