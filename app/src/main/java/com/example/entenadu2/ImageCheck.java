package com.example.entenadu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class ImageCheck extends AppCompatActivity {
    private static final String TAG = ImageCheck.class.getSimpleName();
    ImageView img;
    String imageUrl;
    ProgressDialog progressDialog;
    byte[] bytesArray;
    String getImageUrl;
    Bitmap bitmap;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_check);
        img = findViewById(R.id.imageView_test);
        progressDialog = new ProgressDialog(this);

//        try {

//            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Uid", Context.MODE_PRIVATE);
//            getImageUrl=sharedPreferences.getString("imageArray","");
//            if(getImageUrl !=null)
//            {
//                String[] split=getImageUrl.substring(1,getImageUrl.length()-1).split(", ");
//                bytesArray=new byte[split.length];
//                for(int i=0;i<split.length;i++)
//                {
//                    bytesArray[i]=Byte.parseByte(split[i]);
//                }
//            }
//
//            Log.d(TAG, "image fetch error :" +getImageUrl);
//            ///bytesArray=getImageUrl.getBytes();
//           // bytesArray= Base64.decode(getImageUrl.trim(),Base64.DEFAULT);
//            Log.d(TAG, "bytes array" +bytesArray);
//            bitmap= BitmapFactory.decodeByteArray(bytesArray,0,bytesArray.length);
//            Log.d(TAG, "bitmap image :" +bitmap);
//            img.setImageBitmap(Bitmap.createScaledBitmap(bitmap,img.getWidth(),img.getHeight(),false));


        try {
            FileInputStream fileInputStream = openFileInput("Myfile");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);



        } catch (FileNotFoundException e) {
            Log.d(TAG, "onCreate: "+e);
        }


//            final StorageReference ref;
//            StorageReference storage;
//            storage = FirebaseStorage.getInstance().getReference();
//            ref = storage.child("images").child("imageNo"+1);
//            final long ONE_MEGABYTE = 1024 * 1024;
//            ref.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//                @Override
//                public void onSuccess(byte[] bytes) {
//                    // Data for "images/island.jpg" is returns, use this as needed
//                    /// Log.d(TAG, "image bytes"+sharedPreferences.getString("imageArray",null));
//
//
////                    editor.putString("imageArray",bytes.toString());
////                    editor.commit();
////                    progressDialog.dismiss();
//                    Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                    img.setImageBitmap(Bitmap.createScaledBitmap(bitmap,img.getWidth(),img.getHeight(),false));
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle any errors
//                    Log.d(TAG, "error: "+exception);
//                }
//            });


//        } catch (RuntimeException e) {
//            Log.d(TAG, "image fetch error" + e);
//        }
    }




}
