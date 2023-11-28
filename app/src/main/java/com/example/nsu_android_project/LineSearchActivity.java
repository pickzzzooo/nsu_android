package com.example.nsu_android_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LineSearchActivity extends Activity {
    // info
    private TextView[] infoTxtArr;
    private View[] infoViewArr;
    private Button search_btn;
    private Button time_sheet_btn;
    private Button map_btn;
    private ImageButton bookmark_btn;
    private TextView line_name_txt;
    private EditText search_edit_txt;
    private ImageButton refresh_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_search);

        search_btn = (Button) findViewById(R.id.search_btn);
        time_sheet_btn = (Button) findViewById(R.id.time_sheet_btn);
        map_btn = (Button) findViewById(R.id.map_btn);
        bookmark_btn = (ImageButton) findViewById(R.id.bookmark_btn);
        search_edit_txt = (EditText) findViewById(R.id.serach_edit_text);
        line_name_txt = (TextView ) findViewById(R.id.line_name_txt);
        refresh_btn = (ImageButton) findViewById(R.id.refresh_btn);

        infoViewArr = new View[]{
                findViewById(R.id.view2),
                findViewById(R.id.view3),
                findViewById(R.id.view4),
                findViewById(R.id.view5),
                findViewById(R.id.view6),
                findViewById(R.id.view7),
        };

        infoTxtArr = new TextView[]{
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

        //데이터 불러오기 / 객체생성
        DBManager dbManager = new DBManager(getApplicationContext());

        bookmark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTxt = search_edit_txt.getText().toString();
                if (dbManager.bookMakrDataList.contains(searchTxt)){
                    bookmark_btn.setBackgroundResource(R.drawable.button_normal);
                    dbManager.removeData(getApplicationContext(), searchTxt);
                } else {
                    bookmark_btn.setBackgroundResource(R.drawable.button_pressed);
                    dbManager.addData(getApplicationContext(), searchTxt);
                }
            }
        });

        time_sheet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=" + search_edit_txt.getText().toString() + "역 전철시간표";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://map.naver.com/p/search/" + search_edit_txt.getText().toString() + "역?";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                clickInfoLoadEvent();
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTxt = search_edit_txt.getText().toString();
                if (dbManager.bookMakrDataList.contains(searchTxt)){
                    bookmark_btn.setBackgroundResource(R.drawable.button_pressed);
                } else {
                    bookmark_btn.setBackgroundResource(R.drawable.button_normal);
                }
                clickInfoLoadEvent();
            }
        });
    }

    private void clickInfoLoadEvent() {
        Line1StanNmList line1StanNmList = new Line1StanNmList();
        List<String> soyosanToGuroArr = line1StanNmList.SoyosanToGuroArr;
        List<String> guroToSinchangArr = line1StanNmList.GuroToSinchangArr;
        List<String> guroToIncheonArr = line1StanNmList.GuroToIncheonArr;

        String lineNm = search_edit_txt.getText().toString();

        txtInfoDataLoad(soyosanToGuroArr, lineNm);
        txtInfoDataLoad(guroToSinchangArr, lineNm);
        txtInfoDataLoad(guroToIncheonArr, lineNm);

        for (int i = 2; i < infoTxtArr.length; i++) {infoTxtArr[i].setVisibility(View.INVISIBLE);}
        for (int i = 0; i < infoViewArr.length; i++) {infoViewArr[i].setVisibility(View.INVISIBLE);}

        new GetDataTask().execute();
    }

    private int lineArrIndexSelector(List<String> arr, String lineNm) {
        if (arr.contains(lineNm)){
            int lineIndex = arr.indexOf(lineNm);
            return lineIndex;
        }
        return 100;
    }

    private void txtInfoDataLoad(List<String> arr, String lineNm){
        int lineIndex = lineArrIndexSelector(arr, lineNm);

        line_name_txt.setText(lineNm + "역");

        if (lineIndex != 100) {
            if (lineIndex - 1 >= 0 && arr.get(lineIndex - 1) != null && !arr.get(lineIndex - 1).isEmpty())
            { infoTxtArr[1].setText(arr.get(lineIndex - 1));
            } else {infoTxtArr[1].setText("종점");}
            if ("구로".equals(lineNm)) { infoTxtArr[1].setText("신도림");}
            if ("구일".equals(lineNm)) { infoTxtArr[1].setText("구로");}
            if ("가산디지털단지".equals(lineNm)) { infoTxtArr[1].setText("구로");}
            if ("금천구청".equals(lineNm)) { infoTxtArr[1].setText("독산");}
            if ("병점".equals(lineNm)) { infoTxtArr[1].setText("세류");}

            if (lineIndex + 1 >= 0 && arr.get(lineIndex + 1) != null && !arr.get(lineIndex + 1).isEmpty())
            { infoTxtArr[0].setText(arr.get(lineIndex + 1));
            } else { infoTxtArr[0].setText("종점");}
            if ("구로".equals(lineNm)) { infoTxtArr[0].setText("가산다지털단지/구일 방면");}
            if ("구일".equals(lineNm)) { infoTxtArr[0].setText("개봉");}
            if ("가산디지털단지".equals(lineNm)) { infoTxtArr[0].setText("독산");}
            if ("금천구청".equals(lineNm)) { infoTxtArr[0].setText("석수/광명");}
            if ("병점".equals(lineNm)) { infoTxtArr[0].setText("세마/서동탄");}

            infoTxtArr[0].setText(infoTxtArr[0].getText() + " 방면");
            infoTxtArr[1].setText(infoTxtArr[1].getText() + " 방면");
        }
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {
        private Line1StanNmList line1StanNmList = new Line1StanNmList();
        private List<String> soyosanToGuroArr = line1StanNmList.SoyosanToGuroArr;
        private List<String> guroToSinchangArr = line1StanNmList.GuroToSinchangArr;
        private List<String> guroToIncheonArr = line1StanNmList.GuroToIncheonArr;

        @Override
        protected String doInBackground(Void... params) {
            try {
                String TRAIN_API_KEY = getString(R.string.TRAIN_API_KEY);
                String TYPE = "/json/";
                String START_INDEX = "0/";
                String END_INDEX = "99/";
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
                Log.v("asdf", "Data Load Error");
                e.printStackTrace();
                return null;
            }
        }

        //상행 = 2 하행 = 6
        private void displayInfoTxt(Map<Integer, String> map, Integer updn){
            List<Integer> keySet = new ArrayList<Integer>(map.keySet());
            Collections.sort(keySet);
            int i = updn;
            // 화면에 출력
            for(Integer key : keySet) {
                String infoTxt = map.get(key).toString();
                if (infoTxtArr[i - 1].getText().toString().equals(infoTxt)){continue;}
                if (infoTxtArr[i].getVisibility() == View.INVISIBLE){
                    infoTxtArr[i].setText(infoTxt);
                    infoTxtArr[i].setVisibility(View.VISIBLE);
                }

                for (int ii = 0; ii < Math.min(map.size(), infoViewArr.length); ii++) {
                    if (updn == 2) {
                        infoViewArr[ii].setVisibility(View.VISIBLE);
                    }
                    if (updn == 6 && ii + 3 < infoViewArr.length) {
                        infoViewArr[ii + 3].setVisibility(View.VISIBLE);
                    }
                }
                i += 1;
            }
        }

        // infoText View에 넣을 문자열 만드는 함수.
        private Map<Integer, String> infoTxtBuilder(JSONObject index, Integer ii,Map<Integer,String> map) throws JSONException {
            String statnTnm = index.getString("statnTnm");
            int directAt = index.getInt("directAt");
            int trainSttus = index.getInt("trainSttus");

            StringBuilder newInfoTxt = new StringBuilder();
            newInfoTxt.append(statnTnm);

            if (directAt == 1) {
                newInfoTxt.append("(급)");
            }

            newInfoTxt.append("행\n");
            if (ii == 0) {
                newInfoTxt.append("당역");
            } else {
                newInfoTxt.append(ii);
                newInfoTxt.append("전역");
            }

            if (trainSttus == 0) {
                newInfoTxt.append("접근");
            } else if (trainSttus == 1) {
                newInfoTxt.append("도착");
            } else {
                newInfoTxt.append("출발");
            }

            if (map.size() < 4){
                map.put(ii, newInfoTxt.toString());
            }
            return map;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    List<String> lineArr = new ArrayList<>();
                    Map<Integer, String> upLineSortMap = new HashMap<Integer, String>();
                    Map<Integer, String> dnLineSortMap = new HashMap<Integer, String>();

                    //검색 지하철역 인덱스가 속한 배열 저장
                    String lineNm = search_edit_txt.getText().toString();
                    if (lineArrIndexSelector(soyosanToGuroArr, lineNm) != 100) {lineArr = soyosanToGuroArr;}
                    else if (lineArrIndexSelector(guroToIncheonArr, lineNm) != 100) {lineArr = guroToIncheonArr;}
                    else if (lineArrIndexSelector(guroToSinchangArr, lineNm) != 100) {lineArr = guroToSinchangArr;}

                    JSONObject jsonResponse = new JSONObject(result);
                    JSONArray realtimePositionList = jsonResponse.getJSONArray("realtimePositionList");

                    if (realtimePositionList.length() > 0) {
                        // JSON 행 추적
                        for (int i = 0; i < realtimePositionList.length(); i++){
                            JSONObject index = realtimePositionList.getJSONObject(i);
                            String statnNm = index.getString("statnNm");
                            int lineInt = lineArr.indexOf(lineNm);

                            for (int ii = 0; ii < 6 ; ii++) {
                                int currentIndexUp = lineInt + ii;
                                int currentIndexDown = lineInt - ii;

                                if (currentIndexUp >= 0 && currentIndexUp < lineArr.size()) {
                                    if (statnNm.equals(lineArr.get(currentIndexUp))) {
                                        if (index.getInt("updnLine") == 0) {
                                            infoTxtBuilder(index, ii, upLineSortMap);
                                        }
                                    }
                                }

                                if (currentIndexDown >= 0 && currentIndexDown < lineArr.size()) {
                                    if (statnNm.equals(lineArr.get(currentIndexDown))) {
                                        if (index.getInt("updnLine") == 1) {
                                            infoTxtBuilder(index, ii, dnLineSortMap);
                                        }
                                    }
                                }
                            }
                        }
                        //infotxt 정렬
                        displayInfoTxt(upLineSortMap, 2);
                        displayInfoTxt(dnLineSortMap, 6);
                    } else {
                        Log.v("asdf", "realtimePositionList 배열 존재하지 않음");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v("asdf", "JSON Error");
                }

            } else {
                Log.v("asdf", "Data Load Error");
            }

        }


    }
}



