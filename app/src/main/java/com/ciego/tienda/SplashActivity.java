package com.ciego.tienda;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import cache.AppCache;
import database.CauseDB;
import models.Cause;
import utils.AppUtils;
import webservice.WebServiceCauses;

public class SplashActivity extends ActionBarActivity {

    private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

        initViews();
	}

    private void initViews(){
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        if(AppUtils.checkInternetConnection(this))
            new AsyncLoadCauses().execute();
        else {
            Toast.makeText(this, "Necesitas Intenert", Toast.LENGTH_SHORT).show();
            finish();
        }

        /**AsyncWait asyncWait = new AsyncWait();
        asyncWait.execute();**/

        /**Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                esperar();
            }
        });

        hilo.start();
         **/
    }


    private void esperar(){

        Toast.makeText(this, "Iniciando", Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(7000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Terminando", Toast.LENGTH_SHORT).show();

        //Ocultar la progress bar
        /**runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });**/
    }

    /**
    private class AsyncWait extends AsyncTask<Void, Float, Boolean>{

        @Override
        protected void onPreExecute(){
            //Preparar la tarea
        }

        @Override
        protected Boolean doInBackground(Void... params){
            //Tarea de larga duración



            esperar();
            publishProgress(1.4f, 0.9f);

            return false;
        }

        @Override
        protected void onPostExecute(Boolean param){
            //Finalizar la tarea
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Float... params){
            //Notificar un avance en la tarea
            float uno = params[0];
            float dos = params[1];
        }
    }
     **/


    private class AsyncLoadCauses extends AsyncTask<Void, Void, ArrayList<Cause>>{

        @Override
        protected ArrayList<Cause> doInBackground(Void... params){

            WebServiceCauses ws = new WebServiceCauses();

            try {
                return ws.getCauses();
            }catch (Exception e){
                return new ArrayList<Cause>();
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Cause> param){
            progressBar.setVisibility(View.GONE);

            //Guardar en la cache
            AppCache.setListCauses(param);

            CauseDB causeDB = CauseDB.getInstance(getBaseContext());

            if(param.size() > causeDB.getCauseCount()) {
                causeDB.saveAllCauses(param);
            }

            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }

}
