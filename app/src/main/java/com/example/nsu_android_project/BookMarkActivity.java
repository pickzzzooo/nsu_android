package com.example.nsu_android_project;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookMarkActivity extends Activity {

    private String dataLineNm;
    private TextView[] info1TxtArr;
    private View[] info1ViewArr;
    private TextView[] info2TxtArr;
    private View[] info2ViewArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_activity);

        TextView bookMarkBtnArr[] = new TextView[]{
                findViewById(R.id.bookmark_txt1),
                findViewById(R.id.bookmark_txt2),
                findViewById(R.id.bookmark_txt3),
                findViewById(R.id.bookmark_txt4)
        };

        LinearLayout infoLayoutArr[] = new LinearLayout[]{
                findViewById(R.id.info_layout1),
                findViewById(R.id.info2_layout2),
                findViewById(R.id.info3_layout1),
                findViewById(R.id.info4_layout1)
        };

        DBManager dbManager = new DBManager(getApplicationContext());
        // DBManager 객체 생성 / 즐겨찾기 데이터 불러오기
        for(int i = 0 ; i < dbManager.bookMakrKeyList.size() ; i++) {
            bookMarkBtnArr[i].setText(" " + dbManager.bookMakrDataList.get(i) + "역");
        }



        bookMarkBtnArr[0].setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            info1ViewArr = new View[]{
                    findViewById(R.id.info1_view2),
                    findViewById(R.id.info1_view3),
                    findViewById(R.id.info1_view4),
                    findViewById(R.id.info1_view5),
                    findViewById(R.id.info1_view6),
                    findViewById(R.id.info1_view7)
            };

            info1TxtArr = new TextView[]{
                    findViewById(R.id.info1_upDirTxt1),
                    findViewById(R.id.info1_downDirTxt1),
                    findViewById(R.id.info1_infoTxt1),
                    findViewById(R.id.info1_infoTxt2),
                    findViewById(R.id.info1_book1_infoTxt3),
                    findViewById(R.id.info1_infoTxt4),
                    findViewById(R.id.info1_infoTxt5),
                    findViewById(R.id.info1_infoTxt6),
                    findViewById(R.id.info1_infoTxt7),
                    findViewById(R.id.info1_infoTxt8)
            };
            infoClickEvt(infoLayoutArr[0]);
            dataLineNm = dbManager.bookMakrDataList.get(0);
            clickInfoLoadEvent(info1TxtArr, info1ViewArr);
        }});
        bookMarkBtnArr[1].setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            info1ViewArr = new View[]{
                    findViewById(R.id.info2_view2),
                    findViewById(R.id.info2_view3),
                    findViewById(R.id.info2_view4),
                    findViewById(R.id.info2_view5),
                    findViewById(R.id.info2_view6),
                    findViewById(R.id.info2_view7)
            };

            info1TxtArr = new TextView[]{
                    findViewById(R.id.info2_upDirTxt1),
                    findViewById(R.id.info2_downDirTxt1),
                    findViewById(R.id.info2_infoTxt1),
                    findViewById(R.id.info2_infoTxt2),
                    findViewById(R.id.info2_infoTxt3),
                    findViewById(R.id.info2_infoTxt4),
                    findViewById(R.id.info2_infoTxt5),
                    findViewById(R.id.info2_infoTxt6),
                    findViewById(R.id.info2_infoTxt7),
                    findViewById(R.id.info2_infoTxt8)
            };
            infoClickEvt(infoLayoutArr[1]);
            dataLineNm = dbManager.bookMakrDataList.get(1);
            clickInfoLoadEvent(info1TxtArr, info1ViewArr);
        }});
        bookMarkBtnArr[2].setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            info1ViewArr = new View[]{
                    findViewById(R.id.info3_view2),
                    findViewById(R.id.info3_view3),
                    findViewById(R.id.info3_view4),
                    findViewById(R.id.info3_view5),
                    findViewById(R.id.info3_view6),
                    findViewById(R.id.info3_view7)
            };

            info1TxtArr = new TextView[]{
                    findViewById(R.id.info3_upDirTxt1),
                    findViewById(R.id.info3_downDirTxt1),
                    findViewById(R.id.info3_infoTxt1),
                    findViewById(R.id.info3_infoTxt2),
                    findViewById(R.id.info3_infoTxt3),
                    findViewById(R.id.info3_infoTxt4),
                    findViewById(R.id.info3_infoTxt5),
                    findViewById(R.id.info3_infoTxt6),
                    findViewById(R.id.info3_infoTxt7),
                    findViewById(R.id.info3_infoTxt8)
            };
            infoClickEvt(infoLayoutArr[2]);
            dataLineNm = dbManager.bookMakrDataList.get(2);
            clickInfoLoadEvent(info1TxtArr, info1ViewArr);
        }});
        bookMarkBtnArr[3].setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {infoClickEvt(infoLayoutArr[3]);}});
        info1ViewArr = new View[]{
                findViewById(R.id.info4_view2),
                findViewById(R.id.info4_view3),
                findViewById(R.id.info4_view4),
                findViewById(R.id.info4_view5),
                findViewById(R.id.info4_view6),
                findViewById(R.id.info4_view7)
        };

        info1TxtArr = new TextView[]{
                findViewById(R.id.info4_upDirTxt1),
                findViewById(R.id.info4_downDirTxt1),
                findViewById(R.id.info4_infoTxt1),
                findViewById(R.id.info4_infoTxt2),
                findViewById(R.id.info4_infoTxt3),
                findViewById(R.id.info4_infoTxt4),
                findViewById(R.id.info4_infoTxt5),
                findViewById(R.id.info4_infoTxt6),
                findViewById(R.id.info4_infoTxt7),
                findViewById(R.id.info4_infoTxt8)
        };
        infoClickEvt(infoLayoutArr[2]);
        dataLineNm = dbManager.bookMakrDataList.get(2);
        clickInfoLoadEvent(info1TxtArr, info1ViewArr);
    }

    private void clickInfoLoadEvent(TextView[] txtArr, View[] viewArr) {
        Line1StanNmList line1StanNmList = new Line1StanNmList();
        List<String> soyosanToGuroArr = line1StanNmList.SoyosanToGuroArr;
        List<String> guroToSinchangArr = line1StanNmList.GuroToSinchangArr;
        List<String> guroToIncheonArr = line1StanNmList.GuroToIncheonArr;

        DBManager dbManager = new DBManager(getApplicationContext());


        txtInfoDataLoad(soyosanToGuroArr, dataLineNm, txtArr, viewArr);
        txtInfoDataLoad(guroToSinchangArr, dataLineNm, txtArr, viewArr);
        txtInfoDataLoad(guroToIncheonArr, dataLineNm, txtArr, viewArr);

        for (int i = 2; i < txtArr.length; i++) {txtArr[i].setVisibility(View.INVISIBLE);}
        for (int i = 0; i < viewArr.length; i++) {viewArr[i].setVisibility(View.INVISIBLE);}

        new GetDataTask().execute();
    }

    private void txtInfoDataLoad(List<String> arr, String lineNm, TextView[] txtArr, View[] viewArr){
        int lineIndex = lineArrIndexSelector(arr, lineNm);

        if (lineIndex != 100) {
            if (lineIndex - 1 >= 0 && arr.get(lineIndex - 1) != null && !arr.get(lineIndex - 1).isEmpty())
            { txtArr[1].setText(arr.get(lineIndex - 1));
            } else {txtArr[1].setText("종점");}
            if ("구로".equals(lineNm)) { txtArr[1].setText("신도림");}
            if ("구일".equals(lineNm)) { txtArr[1].setText("구로");}
            if ("가산디지털단지".equals(lineNm)) { txtArr[1].setText("구로");}
            if ("금천구청".equals(lineNm)) { txtArr[1].setText("독산");}
            if ("병점".equals(lineNm)) { txtArr[1].setText("세류");}

            if (lineIndex + 1 >= 0 && arr.get(lineIndex + 1) != null && !arr.get(lineIndex + 1).isEmpty())
            { txtArr[0].setText(arr.get(lineIndex + 1));
            } else { txtArr[0].setText("종점");}
            if ("구로".equals(lineNm)) { txtArr[0].setText("가산다지털단지/구일 방면");}
            if ("구일".equals(lineNm)) { txtArr[0].setText("개봉");}
            if ("가산디지털단지".equals(lineNm)) { txtArr[0].setText("독산");}
            if ("금천구청".equals(lineNm)) { txtArr[0].setText("석수/광명");}
            if ("병점".equals(lineNm)) { txtArr[0].setText("세마/서동탄");}

            txtArr[0].setText(txtArr[0].getText() + " 방면");
            txtArr[1].setText(txtArr[1].getText() + " 방면");
        }
    }
    private void infoClickEvt(LinearLayout layout) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();

        if (layoutParams.weight == 0){layoutParams.weight = 6;}
        else {layoutParams.weight = 0;}
        layout.setLayoutParams(layoutParams);
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

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    List<String> lineArr = new ArrayList<>();
                    Map<Integer, String> upLineSortMap = new HashMap<Integer, String>();
                    Map<Integer, String> dnLineSortMap = new HashMap<Integer, String>();

                    DBManager dbManager = new DBManager(getApplicationContext());

                    //검색 지하철역 인덱스가 속한 배열 저장
                    if (lineArrIndexSelector(soyosanToGuroArr, dataLineNm) != 100) {lineArr = soyosanToGuroArr;}
                    else if (lineArrIndexSelector(guroToIncheonArr, dataLineNm) != 100) {lineArr = guroToIncheonArr;}
                    else if (lineArrIndexSelector(guroToSinchangArr, dataLineNm) != 100) {lineArr = guroToSinchangArr;}

                    JSONObject jsonResponse = new JSONObject(result);
                    JSONArray realtimePositionList = jsonResponse.getJSONArray("realtimePositionList");

                    if (realtimePositionList.length() > 0) {
                        // JSON 행 추적
                        for (int i = 0; i < realtimePositionList.length(); i++){
                            JSONObject index = realtimePositionList.getJSONObject(i);
                            String statnNm = index.getString("statnNm");
                            int lineInt = lineArr.indexOf(dataLineNm);

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
                        displayInfoTxt(upLineSortMap, 2, info1TxtArr, info1ViewArr);
                        displayInfoTxt(dnLineSortMap, 6, info1TxtArr, info1ViewArr);
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

    private int lineArrIndexSelector(List<String> arr, String lineNm) {
        if (arr.contains(lineNm)){
            int lineIndex = arr.indexOf(lineNm);
            return lineIndex;
        }
        return 100;
    }

    private void displayInfoTxt(Map<Integer, String> map, Integer updn, TextView[] txtArr, View[] viewArr){
        List<Integer> keySet = new ArrayList<Integer>(map.keySet());
        Collections.sort(keySet);
        int i = updn;
        // 화면에 출력
        for(Integer key : keySet) {
            String infoTxt = map.get(key).toString();
            if (txtArr[i - 1].getText().toString().equals(infoTxt)){continue;}
            if (txtArr[i].getVisibility() == View.INVISIBLE){
                txtArr[i].setText(infoTxt);
                txtArr[i].setVisibility(View.VISIBLE);
            }

            for (int ii = 0; ii < Math.min(map.size(), viewArr.length); ii++) {
                if (updn == 2) {
                    viewArr[ii].setVisibility(View.VISIBLE);
                }
                if (updn == 6 && ii + 3 < viewArr.length) {
                    viewArr[ii + 3].setVisibility(View.VISIBLE);
                }
            }
            i += 1;
        }
    }
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
}

