package mx.unam.ingenieria.thinq.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Imagenes {
    public static ArrayList<String> Imagenes(Context context) {
        Uri uri;
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        int column_index_data;
        Cursor cursor;
        ArrayList<String> AllImages=new ArrayList<>();
        String AbsoultePathOfImage;
        uri= MediaStore.Images.Media.INTERNAL_CONTENT_URI;//corregir aqui
        Log.d("Ojo",String.valueOf(uri));

        String[] projection={MediaStore.MediaColumns.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        //String orderBy=MediaStore.Video.Media.DATE_TAKEN;
        cursor= context.getContentResolver().query(uri,projection,null,null,null);
        column_index_data=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext())
        {
            AbsoultePathOfImage=cursor.getString(column_index_data);
            Log.d("Ojon",AbsoultePathOfImage);
            AllImages.add(AbsoultePathOfImage);
        }

        return  AllImages;
    }
}
