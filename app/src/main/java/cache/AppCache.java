package cache;

import java.util.ArrayList;

import models.Cause;

/**
 * Created by Ciego on 20/12/2014.
 */
public class AppCache {

    private static ArrayList<Cause> listCauses;

    public static ArrayList<Cause> getListCauses(){
        return listCauses;
    }

    public static void setListCauses(ArrayList<Cause> listCauses){
        AppCache.listCauses = listCauses;
    }
}
