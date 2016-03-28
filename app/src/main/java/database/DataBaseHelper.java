package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ciego on 20/12/2014.
 */
public class DataBaseHelper  extends SQLiteOpenHelper{

    public final static String DATABASE_NAME = "Tienda";

    public final static String TABLE_CAUSES = "Causes";

    private final String createCauses =
            "CREATE TABLE " + TABLE_CAUSES + " (id TEXT, title TEXT, days INTEGER, support INTEGER)";

    private final String dropCauses = "DROP TABLE IF EXIST " + TABLE_CAUSES;


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createCauses);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropCauses);
        db.execSQL(createCauses);
    }
}
