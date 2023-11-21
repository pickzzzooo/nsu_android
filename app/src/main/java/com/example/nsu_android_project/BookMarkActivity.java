package com.example.nsu_android_project;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class BookMarkActivity extends Activity {
    TextView dataTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_activity);
        dataTxt = findViewById(R.id.dataTxt);

        new GetDataTask().execute();
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                String TRAIN_API_KEY = getString(R.string.TRAIN_API_KEY);
                String TYPE = "/json/";
                String START_INDEX = "0/";
                String END_INDEX = "1/";
                String subwayNm = "1호선";
                String queryUrl = "http://swopenAPI.seoul.go.kr/api/subway/" + TRAIN_API_KEY + TYPE + "realtimePosition/"
                        + START_INDEX + END_INDEX + subwayNm;

                URL url = new URL(queryUrl);
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);

                StringBuilder buffer = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                return buffer.toString();
            } catch (Exception e) {
                Log.v("asdf", "error");
                e.printStackTrace();
                return null;
            }
        }
        // 로그 확인
        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                dataTxt.setText(result);
                Log.v("asdf", "성공");
            } else {
                Log.v("asdf", "error");
            }
        }
    }
}

