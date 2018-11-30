package com.twoyul.myapplication;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    //사업소 정보
    private TextView mSpot;
    private TextView dddprc;
    //대기오염 정보
    private TextView mQno2;
    private TextView mQso2;
    private TextView mQpms;
    //수질오염 정보
    private TextView mQph;
    private TextView mQcod;
    private TextView mQss;
    //기상 정보
    private TextView qtep;//온도
    private TextView qhmd;//습도
    private TextView qapr;//기압
    private TextView qdwd;//풍향
    private TextView qvwd;//풍속
    private TextView qarf;//강수량

    private String[][] info = new String[3][7];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.pageTitle);

        dddprc = (TextView) rootView.findViewById(R.id.tv_sub_CAI_value);

        mQno2 = (TextView) rootView.findViewById(R.id.tv_main_detail_NO_value);
        mQso2 = (TextView) rootView.findViewById(R.id.tv_main_detail_SOx_value);
        mQpms = (TextView) rootView.findViewById(R.id.tv_main_detail_dust_value);

        mQph = (TextView) rootView.findViewById(R.id.tv_main_detail2_PH_value);
        mQcod = (TextView) rootView.findViewById(R.id.tv_main_detail2_COD_value);
        mQss = (TextView) rootView.findViewById(R.id.tv_main_detail2_SS_value);

        qtep = (TextView) rootView.findViewById(R.id.tv_sub_PM10_value);
        qhmd = (TextView) rootView.findViewById(R.id.tv_sub_PM25_value);
        qapr = (TextView) rootView.findViewById(R.id.tv_sub_O3_value);
        qdwd = (TextView) rootView.findViewById(R.id.tv_sub_NO2_value);
        qvwd = (TextView) rootView.findViewById(R.id.tv_sub_CO_value);
        qarf = (TextView) rootView.findViewById(R.id.tv_sub_SO2_value);

        String strOrgCd, strSnumPos;

        switch(getArguments().getInt(ARG_SECTION_NUMBER)){
            case 1:
                textView.setText("신인천복합화력발전소");
                //busan - test
                strOrgCd = "209";
                strSnumPos = "01";
                setData(strOrgCd, strSnumPos);
                break;
            case 2:
                textView.setText("삼척그린파워발전소");
                //busan - test
                strOrgCd = "855";
                strSnumPos = "01";
                break;
            case 3:
                textView.setText("영월복합화력발전");
                break;
            case 4:
                textView.setText("안동복합화력발전소");
                break;
            case 5:
                textView.setText("부산복합화력발전소");
                break;
            case 6:
                textView.setText("하동화력발전소");
                break;
            case 7:
                textView.setText("남제주화력발전소");
                break;
        }

        return rootView;
    }

    public void setData(String strOrgCd, String strSnumPos){

        DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
        Date nowDate = new Date();
        String strSdate = sdFormat.format(nowDate);
        String strEdate = strSdate;
        String serviceKey = "I6Shlm9z41H3%2FQKLtAdPNH2IhLU8fQzJQNJPiqX8uhXsZ%2BH3T0Y3MZGKdp8frzyRGskrr0Y7G4rT6RPLzvRUwg%3D%3D";

        String url_a = "http://dataopen.kospo.co.kr/openApi/Conce/ardConcentration"
                + "?strOrgCd=" + strOrgCd
                + "&strSnumPos=" + strSnumPos
                + "&strSdate=" + strSdate
                + "&strEdate=" + strEdate
                + "&numOfRows=1&pageNo=1"
                +"&serviceKey=" + serviceKey;

        String url_w = "http://dataopen.kospo.co.kr/openApi/Conce/ardWather"
                + "?strOrgCd=" + strOrgCd
                + "&strSdate=" + strSdate
                + "&strEdate=" + strEdate
                + "&numOfRows=1&pageNo=1"
                +"&serviceKey=" + serviceKey;

        String url_s = "http://dataopen.kospo.co.kr/openApi/Conce/ardWeather"
                + "?strOrgCd=" + strOrgCd
                + "&strSdate=" + strSdate
                + " &strEdate=" + strEdate
                + "&numOfRows=11&pageNo=1"
                + "&serviceKey=" + serviceKey;

        try {
            TmpThread tt_a = new TmpThread(url_a, null, 'a');

            tt_a.execute();

        }catch(java.lang.Exception ex){

            mQno2.setText("-");
            mQso2.setText("-");
            mQpms.setText("-");

            ex.printStackTrace();
        }

        try {
            TmpThread tt_w = new TmpThread(url_w, null, 'w');

            tt_w.execute();

        }catch(java.lang.Exception ex){

            mQph.setText("-");
            mQcod.setText("-");
            mQss.setText("-");

            ex.printStackTrace();
        }

        try {
            TmpThread tt_s = new TmpThread(url_s, null, 's');

            tt_s.execute();

        }catch(java.lang.Exception ex){
            qtep.setText("-");
            qhmd.setText("-");
            qapr.setText("-");
            qdwd.setText("-");
            qvwd.setText("-");
            qarf.setText("-");

            ex.printStackTrace();
        }

    }

    class TmpThread extends AsyncTask {
        private String url;
        private ContentValues values;
        private char check;
        //public NetworkTask(String url, ContentValues values) {
        public TmpThread(String url, ContentValues values, char check) {
            this.url = url;
            this.values = values;
            this.check = check;
        }


        protected Object doInBackground(Object[] params) {
            String result;

            HTTPConnection HttpConnection = new HTTPConnection();

            result = HttpConnection.request(url, values);

            PollDTO PD = PollService.getData(result);

            return PD;
        }

        protected void onPostExecute(Object o) {
            Log.d("PD", o.toString());

            PollDTO PD = (PollDTO) o;

            switch (check){
                case 'a':
                    info[0][0] = PD.getSaupso();
                    info[0][1] = PD.getQno2();
                    info[0][2] = PD.getQso2();
                    info[0][3] = PD.getQpms();
                    mSpot.setText(info[0][0]);
                    mQno2.setText(info[0][1]);
                    mQso2.setText(info[0][2]);
                    mQpms.setText(info[0][3]);

                    break;

                case 'w':
                    info[1][0] = PD.getDddprc();
                    info[1][1] = PD.getQph();
                    info[1][2] = PD.getQcod();
                    info[1][3] = PD.getQss();
                    dddprc.setText(info[1][0]);
                    mQph.setText(info[1][1]);
                    mQcod.setText(info[1][2]);
                    mQss.setText(info[1][3]);

                    break;

                case 's':
                    info[2][1] = PD.getQtep();
                    info[2][2] = PD.getQhmd();
                    info[2][3] = PD.getQapr();
                    info[2][4] = PD.getQdwd();
                    info[2][5] = PD.getQvwd();
                    info[2][6] = PD.getQarf();

                    qtep.setText(info[2][1]);
                    qhmd.setText(info[2][2]);
                    qapr.setText(info[2][3]);
                    qdwd.setText(info[2][4]);
                    qvwd.setText(info[2][5]);
                    qarf.setText(info[2][6]);

                    break;

                default:
            }
        }
    }
}


