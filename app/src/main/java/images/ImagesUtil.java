package images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ciego.tienda.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import models.Cause;

/**
 * Created by Ciego on 20/12/2014.
 */
public class ImagesUtil {

    public static Bitmap getBitmapFromURL(String source) throws IOException{
        URL url = new URL(source);

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap bitmap = BitmapFactory.decodeStream(input);
        return bitmap;
    }

    public static boolean existCauseImage(Context context, Cause cause){

        if(cause == null)
            return false;
        else{
            String imageName = "imageCause_" + cause.getId();
            File file = context.getFileStreamPath(imageName);
            if(file.exists())
                return true;
            return false;
        }
    }

    public static void saveCauseImage(Context context, Cause cause){
        String url = cause.getUrlImage();
        String imageName = "imageCause_" + cause.getId();

        if(!existCauseImage(context, cause)){
            try {
                Bitmap bitmap = getBitmapFromURL(url);
                FileOutputStream output = context.openFileOutput(imageName, Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.PNG,90,output);
                output.close();
            }catch (Exception e){

            }
        }

    }

    public static Bitmap getCauseImage(Context context, Cause cause){

        String imageName = "imageCause_" + cause.getId();

        try {
            InputStream is = context.openFileInput(imageName);
            return BitmapFactory.decodeStream(is);
        }catch (FileNotFoundException e){
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder);
        }
    }
}
