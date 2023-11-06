package com.example.nsu_android_project.LineMaps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.nsu_android_project.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Line1MapActivity extends Activity {
    public static Context context;
    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text = (TextView) findViewById(R.id.textView);
        getData();
        context = this;
    }

    public void getData () {
        StringBuffer buffer = new StringBuffer();
        new Thread() {
            @Override
            public void run() {
                String TRAIN_API_KEY = getString(R.string.TRAIN_API_KEY);
                String TYPE = "/json/";
                String START_INDEX = "0/";
                String END_INDEX = "1/";
                String subwayNm = "1호선";
                String queryUrl = "http://swopenAPI.seoul.go.kr/api/subway/" + TRAIN_API_KEY + TYPE + "realtimePosition/"
                        + START_INDEX + END_INDEX + subwayNm;

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

                    JSONObject dataJSON = new JSONObject();


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
