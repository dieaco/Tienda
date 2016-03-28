package com.ciego.tienda;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import dialogs.ListDialog;
import dialogs.OptionsDialog;
import dialogs.SimpleDialog;


public class Store extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
    }


    public void showDialog(){

    }

    public void doAction(){
        finish();
    }

    private void launchDialog1(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleDialog dialog = new SimpleDialog();
        dialog.show(fragmentManager, "DIALOG");

    }

    private void launchDialog2(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        OptionsDialog dialog = new OptionsDialog();
        dialog.show(fragmentManager, "DIALOG");

    }

    private void launchDialog3(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        ListDialog dialog = ListDialog.newInstance("Hola Mundo");
        dialog.show(fragmentManager, "DIALOG");

    }
}
