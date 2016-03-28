package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ciego on 20/12/2014.
 */
public class AppUtils {

    public static boolean checkInternetConnection(Context context){

        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = manager.getActiveNetworkInfo();

        if(info == null)
            return false;
        if(!info.isConnected())
            return false;
        if(!info.isAvailable())
            return false;

        return true;
    }
}
