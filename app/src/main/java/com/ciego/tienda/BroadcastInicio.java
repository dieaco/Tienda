package com.ciego.tienda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ciego on 27/12/2014.
 */
public class BroadcastInicio extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("TAG", "Se prendió el teléfono");
        Toast.makeText(context, "Se prendió el teléfono", Toast.LENGTH_SHORT).show();
    }


}
