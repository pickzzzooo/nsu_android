package com.example.nsu_android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text);

        getData();
    }

    public void getData () {
        StringBuffer buffer=new StringBuffer();
        new Thread() {
            @Override
            public  void run(){
                String TRAIN_API_KEY = getString(R.string.TRAIN_API_KEY);
                String TYPE = "json";
                String SERVICE = "realtimePosition";
                Integer START_INDEX = 0;
                Integer END_INDEX = 10;
                String subwayNm = "1호선";
                String queryUrl = "http://swopenapi.seoul.go.kr/api/subway/sample/json/realtimeStationArrival/1/1/1호선";
                //String queryUrl = "http://swopenAPI.seoul.go.kr/api/subway/" + TRAIN_API_KEY + "/realtimePosition/0/5/1호선";
                try {
                    URL url = new URL(queryUrl);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    StringBuffer buffer = new StringBuffer();
                    String line = reader.readLine();
                    while (line != null) {
                        buffer.append(line + "\n");
                        line = reader.readLine();
                    }

                    //로그 출력
                    Log.v("asdf", "성공");
                    text.setText(buffer);

                } catch (Exception e) {
                    Log.v("asdf", "error");
                    e.printStackTrace();
                }


            }
        }.start();
    }
}

