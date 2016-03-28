package webservice;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import models.Cause;

/**
 * Created by Ciego on 20/12/2014.
 */
public class WebServiceCauses {


    private HttpClient client;
    private String url;

    public WebServiceCauses(){

        url = "http://192.168.1.105:1337/summary";

        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        client = new DefaultHttpClient(params);
    }

    private JSONArray consultWebService(){

        HttpGet get = new HttpGet(url);

        HttpResponse response = null;

        try {
            response = client.execute(get);
        }catch(ClientProtocolException e){
            //No está pemritido
            e.printStackTrace();
            return null;
        }catch (IOException e){
            //Error IO
            e.printStackTrace();
            return null;
        }

        String stringResponse = null;

        try {
            stringResponse = EntityUtils.toString(response.getEntity());
        }catch (IOException e){
            //Error IO
            e.printStackTrace();
            return null;
        }

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(stringResponse);
        }catch (JSONException e){
            //No es un JSON
            e.printStackTrace();
            return null;
        }

        return jsonArray;
    }


    public ArrayList<Cause> getCauses(){
        JSONArray array = consultWebService();

        ArrayList<Cause> listCauses = new ArrayList<Cause>();

        for(int i=0; i <array.length(); i++)
        {
            try{

                JSONObject json = array.getJSONObject(i);
                Cause cause = parseCause(json);
                listCauses.add(cause);

            }catch(JSONException e){
                e.printStackTrace();
            }
        }

        return listCauses;
    }

    private Cause parseCause(JSONObject json){
        String title = "";
        int daysRemaining = 0;
        int percentage = 0;
        String urlImage = "";
        ArrayList<Integer> supporters = new ArrayList<Integer>();
        String id = "";

        try {
            title = json.getString("titile");
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            daysRemaining = json.getInt("days");
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            percentage = json.getInt("support");
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            urlImage = json.getString("image");
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            id = json.getString("id");
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = json.getJSONArray("supporters");
            for(int i = 0; i < jsonArray.length(); i++){
                supporters.add(jsonArray.getInt(i));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        Cause cause = new Cause(title, daysRemaining,percentage,urlImage,supporters, id);

        return cause;
    }
}
