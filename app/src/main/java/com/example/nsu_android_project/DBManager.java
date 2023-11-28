package com.example.nsu_android_project;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBManager {
    public List<String> bookMakrDataList = new ArrayList(Arrays.asList("null", "null", "null", "null"));
    public static final List<String> bookMakrKeyList = new ArrayList<>(Arrays.asList("BOOKMARK_1", "BOOKMARK_2", "BOOKMARK_3",
            "BOOKMARK_4"));

    DBManager(Context context){
        for(int i = 0 ; i < bookMakrKeyList.size() ; i++){
            String dummy = getStringArrayPref(context, bookMakrKeyList.get(i));
            bookMakrDataList.set(i, dummy);
        }
        //불러온 데이터 확인 로그
        for(int i = 0 ; i < bookMakrDataList.size() ; i++) {
            Log.v("asdf", bookMakrDataList.get(i));
        }
    }

    public void addData(Context context, String value) {
        bookMakrDataList.add(0, value);

        for(int i = 0 ; i < bookMakrKeyList.size() ; i++) {
            setStringArrayPref(context, bookMakrKeyList.get(i), bookMakrDataList.get(i));
            Log.v("asdf", bookMakrDataList.get(i));
        }
    }

    public void removeData(Context context, String value){
        bookMakrDataList.remove(value);
        bookMakrDataList.add("null");
        for (int i = 0 ; i < bookMakrKeyList.size() ; i++) {
            setStringArrayPref(context, bookMakrKeyList.get(i), bookMakrDataList.get(i));
        }
    }

    public String getStringArrayPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(key, Activity.MODE_PRIVATE);
        String data = prefs.getString(key, null);
        return data;
    }

    public void setStringArrayPref(Context context, String key, String values) {
        SharedPreferences prefs = context.getSharedPreferences(key, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key, values);
        editor.apply();
    }
}
