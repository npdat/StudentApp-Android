package com.example.dc2dev.studentapp.data.clients.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;

/**
 * Created by dc2dev on 6/15/17.
 */

public class BitmapByte {
    public static byte [] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }
    public static Bitmap getBitmap(byte[] img){
        return BitmapFactory.decodeByteArray(img,0,img.length);
    }
    public static  Bitmap uritoBM(Uri uri, Context context){
        Bitmap bitmap=null;
        try {
            ParcelFileDescriptor parcelFileDescriptor=context.getContentResolver().openFileDescriptor(uri,"r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            bitmap=BitmapFactory.decodeFileDescriptor(fileDescriptor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }



}
