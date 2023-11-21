package com.example.nsu_android_project;

import static android.provider.Settings.System.getString;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ScaleGestureDetector;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
public class LineSearchActivity extends Activity {
    // info
    private TextView[] infoTxtAr;
    private View[] infoViewArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_search);

        infoTxtAr = new TextView[]{
                findViewById(R.id.upDirTxt),
                findViewById(R.id.downDirTxt),
                findViewById(R.id.infoTxt1),
                findViewById(R.id.infoTxt2),
                findViewById(R.id.infoTxt3),
                findViewById(R.id.infoTxt4),
                findViewById(R.id.infoTxt5),
                findViewById(R.id.infoTxt6),
                findViewById(R.id.infoTxt7),
                findViewById(R.id.infoTxt8),
        };

        new GetDataTask().execute();
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {
        private String TRAIN_API_KEY = getString(R.string.TRAIN_API_KEY);
        private String TYPE = "/json/";
        private String START_INDEX = "0/";
        private String END_INDEX = "1/";
        private String subwayNm = "1호선";
        private String queryUrl = "http://swopenAPI.seoul.go.kr/api/subway/" + TRAIN_API_KEY + TYPE + "realtimePosition/"
                + START_INDEX + END_INDEX + subwayNm;
        @Override
        protected String doInBackground(Void... params) {
            try {

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
                infoTxtAr[1].setText(result);
                Log.v("asdf", "성공");
            } else {
                Log.v("asdf", "error");
            }
        }
    }
}



