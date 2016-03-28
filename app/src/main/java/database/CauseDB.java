package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import models.Cause;

/**
 * Created by Ciego on 20/12/2014.
 */
public class CauseDB {

    private static CauseDB causeDB;

    private DataBaseHelper dbHelper;

    private CauseDB(Context context){
        dbHelper = new DataBaseHelper(context, DataBaseHelper.DATABASE_NAME, null, 1);
    }

    public static CauseDB getInstance(Context context){

        if(causeDB == null)
            causeDB = new CauseDB(context);

        return causeDB;
    }

    public void saveCause(Cause cause){

        //Primero obtenemos la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //(id TEXT, title TEXT, days INTEGER, support INTEGER)
        ContentValues values= new ContentValues();
        values.put("id", cause.getId());
        values.put("title", cause.getTitle());
        values.put("days", cause.getDaysRemaining());
        values.put("support", cause.getPercentage());

        //Insertamos en la base de datos
        db.insert(DataBaseHelper.TABLE_CAUSES, null, values);
        db.close();

        Log.i("TAG", "Nueva fila");
    }

    public int getCauseCount(){
        return getCauses().size();
    }

    public void saveAllCauses(ArrayList<Cause> listCauses){

        Iterator<Cause> iterator = listCauses.iterator();
        while(iterator.hasNext()){
            Cause cause = iterator.next();
            saveCause(cause);
        }

    }

    public ArrayList<Cause> getCauses(){

        ArrayList<Cause> listCauses = new ArrayList<Cause>();

        //Obtener base de datos para solo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_CAUSES, null);

        if(cursor.moveToFirst()){
            //Si hay datos
            do{

                String id = cursor.getString(0);
                String title = cursor.getString(1);
                int daysReamaining = cursor.getInt(2);
                int percentage = cursor.getInt(3);

                Cause cause = new Cause(title, daysReamaining, percentage, id);

                //Insertarla en los resultados
                listCauses.add(cause);

            }while (cursor.moveToNext());
        }

        //Cerrar la base de datos hasta el final
        db.close();

        return listCauses;
    }

    public void editCause(String id, Cause newCause){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put("id", newCause.getId());
        values.put("title", newCause.getTitle());
        values.put("days", newCause.getDaysRemaining());
        values.put("support", newCause.getPercentage());

        String whereArgs[] = {id};

        db.update(DataBaseHelper.TABLE_CAUSES, values, "id = ?", whereArgs);
        db.close();


    }

    public void deleteCause(Cause cause){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereArgs[] = {cause.getId()};

        db.delete(DataBaseHelper.TABLE_CAUSES, "id = ?", whereArgs);
        db.close();

    }
}
