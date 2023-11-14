package com.example.nsu_android_project;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ScaleGestureDetector;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
public class LineSearchActivity extends Activity {
    TextView text;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private LinearLayout search_page_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_search);

        search_page_container = (LinearLayout) findViewById(R.id.search_page_container);

        // 핀치 줌 호출
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        getData();
    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();

            // 최대 10배, 최소 10배 줌 한계 설정
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            search_page_container.setScaleX(mScaleFactor);
            search_page_container.setScaleY(mScaleFactor);
            return true;
        }
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

