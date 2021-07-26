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

        //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //int column_index_data;
        //Cursor cursor;
        //Uri uri;
        ArrayList<String> AllImages=new ArrayList<>();
        String AbsoultePathOfImage = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() ;
        File file = new File(AbsoultePathOfImage);
        File[] files = file.listFiles();
        ArrayList<String> imagePaths = new ArrayList<String>();
        for (int i = 0; i < files.length; i++)
        {
            imagePaths.add(files[i].getAbsolutePath());
        }
        return imagePaths;
        /*

        uri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;//corregir aqui
        Log.d("Ojo",String.valueOf(uri));

        String[] projection={MediaStore.MediaColumns.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        //String orderBy=MediaStore.Video.Media.DATE_TAKEN;
        cursor= context.getContentResolver().query(uri,projection,null,null,null);
        column_index_data=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        //FUNCIONAL PERO NO ENCUENTRA LA RUTA DE LA APP
    */
        /*
        String uri= MediaStore.Images.Media.DATA;//corregir aqui
        String condition = uri + " like '%/Pictures/%'";

        String[] projection={MediaStore.MediaColumns.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.SIZE};
        //String orderBy=MediaStore.Video.Media.DATE_TAKEN;
        cursor= context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection,condition,null,null);
        column_index_data=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        //SE PUEDE BUSCAR PERO AUN NO LOGRO ACCEDER A LA APP
*/
        /*
        File file=new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Fotos_ThinQ");

        uri= Uri.fromFile(file);
        Log.d("Ojo",String.valueOf(uri));

        String[] projection={MediaStore.MediaColumns.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        //String orderBy=MediaStore.Video.Media.DATE_TAKEN;
        cursor= context.getContentResolver().query(uri,projection,null,null,null);
        column_index_data=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
*/
        /*
        while (cursor.moveToNext())
        {
            AbsoultePathOfImage=cursor.getString(column_index_data);
            Log.d("Ojo",String.valueOf(AbsoultePathOfImage));
            AllImages.add(AbsoultePathOfImage);
        }
*/
        //return  AllImages;
    }
}
