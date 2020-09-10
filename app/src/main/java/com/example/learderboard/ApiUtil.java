package com.example.learderboard;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){}
    public static final String BASE_API_URL=" https://gadsapi.herokuapp.com/api/skilliq";
    public static final String BASE_HOUR_URL=" https://gadsapi.herokuapp.com/api/hours";
    public static final String QUERY_PARAMETER_KEY="q";
    public static final String API_KEY="";
    public static final String KEY="key";

    public static URL buildUrl(String title){

        URL url=null;
        Uri uri=Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY,title)
                .appendQueryParameter(KEY,API_KEY)
                .build();
        try {
            url=new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static URL buildHourUrl(String title){
        URL url=null;
        Uri uri=Uri.parse(BASE_HOUR_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY,title)
                .appendQueryParameter(KEY,API_KEY)
                .build();
        try {
            url=new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }
    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();

        try {
            InputStream stream=connection.getInputStream();
            Scanner scanner=new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasdata=scanner.hasNext();
            if (hasdata){
                return scanner.next();
            }else {
                return null;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log.d("Error",e.getMessage());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }
    public static ArrayList<Leader> getLeadersFromJson(String json){
        final String ID="id";
        final String NAME="name";
        final String SCORE="score";
        final String COUNTRY="country";
        final String IMAGEURL="badgeUrl";
        ArrayList<Leader> leaders=new ArrayList<Leader>();
        try {
            //JSONObject jsonLeaders=new JSONObject(json);
            //JSONArray arrayLeaders=jsonLeaders.getJSONArray("");
            JSONArray arrayLeaders=new JSONArray(json);
            int numberOfLeaders=arrayLeaders.length();
            for (int i=0;i<numberOfLeaders;i++){
                JSONObject leadeJSON=arrayLeaders.getJSONObject(i);
                Leader leader=new Leader(leadeJSON.getString(NAME),leadeJSON.getString(SCORE),leadeJSON.getString(COUNTRY),leadeJSON.getString(IMAGEURL));
                leaders.add(leader);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return leaders;
    }
    public static ArrayList<HourLeaders> getHourLeadersFromJson(String json) {
        final String ID = "id";
        final String NAME = "name";
        final String COUNTRY = "country";
        final String HOURS = "hours";
        final String IMAGEURL="badgeUrl";
        ArrayList<HourLeaders> hourLeaders = new ArrayList<HourLeaders>();
        try {
            //JSONObject jsonLeaders=new JSONObject(json);
            //JSONArray arrayLeaders=jsonLeaders.getJSONArray("");
            JSONArray arrayLeaders = new JSONArray(json);
            int numberOfLeaders = arrayLeaders.length();
            for (int i = 0; i < numberOfLeaders; i++) {
                JSONObject leadeJSON = arrayLeaders.getJSONObject(i);
                HourLeaders hourLeader = new HourLeaders(leadeJSON.getString(NAME), leadeJSON.getString(COUNTRY), leadeJSON.getString(HOURS),leadeJSON.getString(IMAGEURL));
                hourLeaders.add(hourLeader);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hourLeaders;

    }

}
