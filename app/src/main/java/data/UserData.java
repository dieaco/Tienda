package data;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {
	
	
	//Consultar una preferencia
	public static boolean isLogged(Context context){
		SharedPreferences preferences = 
				context.getSharedPreferences("USER DATA", Context.MODE_PRIVATE);
		
		return preferences.getBoolean("IS_LOGGED", false);
	}
	
	//escribir una preferencia
	public static void setLogged(Context context, boolean isLogged){
		SharedPreferences preferences = 
				context.getSharedPreferences("USER DATA", Context.MODE_PRIVATE);
		
		SharedPreferences.Editor edit = preferences.edit();
		edit.putBoolean("IS_LOGGED", isLogged);
		edit.commit();
	}

}
