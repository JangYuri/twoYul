package com.twoyul.myapplication;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twoyul.myapplication.Model.PollDTO;
import com.twoyul.myapplication.Network.HTTPConnection;
import com.twoyul.myapplication.Network.PollService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "strOrgCd";
    private static final String ARG_PARAM2 = "strSnumPos";
    private static final String ARG_PARAM3 = "plantName";

    private String strOrgCd;
    private String strSnumPos;
    private String plantName;

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

    ProgressDialog asyncDialog;

    public static InfoFragment newInstance(String param1, String param2, String param3) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.strOrgCd = getArguments().getString(ARG_PARAM1);
            this.strSnumPos = getArguments().getString(ARG_PARAM2);
            this.plantName = getArguments().getString(ARG_PARAM3);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        asyncDialog = new ProgressDialog(getContext());
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("불러오는 중입니다...");

        TextView pageTitle = rootView.findViewById(R.id.pageTitle);
        pageTitle.setText(this.plantName);

        mSpot = rootView.findViewById(R.id.tv_main_spot);
        dddprc = rootView.findViewById(R.id.tv_sub_CAI_value);

        mQno2 = rootView.findViewById(R.id.tv_main_detail_NO_value);
        mQso2 = rootView.findViewById(R.id.tv_main_detail_SOx_value);
        mQpms = rootView.findViewById(R.id.tv_main_detail_dust_value);

        mQph = rootView.findViewById(R.id.tv_main_detail2_PH_value);
        mQcod = rootView.findViewById(R.id.tv_main_detail2_COD_value);
        mQss = rootView.findViewById(R.id.tv_main_detail2_SS_value);

        qtep = rootView.findViewById(R.id.tv_sub_PM10_value);
        qhmd = rootView.findViewById(R.id.tv_sub_PM25_value);
        qapr = rootView.findViewById(R.id.tv_sub_O3_value);
        qdwd = rootView.findViewById(R.id.tv_sub_NO2_value);
        qvwd = rootView.findViewById(R.id.tv_sub_CO_value);
        qarf = rootView.findViewById(R.id.tv_sub_SO2_value);

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
            + "&serviceKey=" + serviceKey;

        String url_w = "http://dataopen.kospo.co.kr/openApi/Conce/ardWather"
            + "?strOrgCd=" + strOrgCd
            + "&strSdate=" + strSdate
            + "&strEdate=" + strEdate
            + "&numOfRows=1&pageNo=1"
            + "&serviceKey=" + serviceKey;

        String url_s = "http://dataopen.kospo.co.kr/openApi/Conce/ardWeather"
            + "?strOrgCd=" + strOrgCd
            + "&strSdate=" + strSdate
            + " &strEdate=" + strEdate
            + "&numOfRows=11&pageNo=1"
            + "&serviceKey=" + serviceKey;


        try {
            TmpThread tt_a = new TmpThread(url_a, null, 'a');

            tt_a.execute();

        } catch (Exception ex) {

            mQno2.setText("-");
            mQso2.setText("-");
            mQpms.setText("-");

            ex.printStackTrace();
        }

        try {
            TmpThread tt_w = new TmpThread(url_w, null, 'w');

            tt_w.execute();

        } catch (Exception ex) {

            mQph.setText("-");
            mQcod.setText("-");
            mQss.setText("-");

            ex.printStackTrace();
        }

        try {
            TmpThread tt_s = new TmpThread(url_s, null, 's');

            tt_s.execute();

        } catch (Exception ex) {
            qtep.setText("-");
            qhmd.setText("-");
            qapr.setText("-");
            qdwd.setText("-");
            qvwd.setText("-");
            qarf.setText("-");

            ex.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.println("[" + i + "]" + "[" + j + "] = " + info[i][j]);
            }
        }

        return rootView;
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

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            asyncDialog.show();
        }

        protected Object doInBackground(Object[] params) {
            String result;

            HTTPConnection HttpConnection = new HTTPConnection();

            result = HttpConnection.request(url, values);

            PollDTO PD = PollService.getData(result);

            Log.d("PDLogging", PD.toString());

            return PD;
        }

        protected void onPostExecute(Object o) {
            PollDTO PD = (PollDTO) o;

            switch (check) {
                case 'a':
//                    info[0][0] = PD.getSaupso();
//                    info[0][1] = PD.getQno2();
//                    info[0][2] = PD.getQso2();
//                    info[0][3] = PD.getQpms();
//                    mSpot.setText(info[0][0]);
//                    mQno2.setText(info[0][1]);
//                    mQso2.setText(info[0][2]);
//                    mQpms.setText(info[0][3]);

                    mQno2.setText(PD.getQno2());
                    mQso2.setText(PD.getQso2());
                    mQpms.setText(PD.getQpms());

                    break;

                case 'w':
//                    info[1][0] = PD.getDddprc();
//                    info[1][1] = PD.getQph();
//                    info[1][2] = PD.getQcod();
//                    info[1][3] = PD.getQss();
//                    dddprc.setText(info[1][0]);
//                    mQph.setText(info[1][1]);
//                    mQcod.setText(info[1][2]);
//                    mQss.setText(info[1][3]);

                    dddprc.setText(PD.getDddprc());
                    mQph.setText(PD.getQph());
                    mQcod.setText(PD.getQcod());
                    mQss.setText(PD.getQss());

                    break;

                case 's':
//                    info[2][1] = PD.getQtep();
//                    info[2][2] = PD.getQhmd();
//                    info[2][3] = PD.getQapr();
//                    info[2][4] = PD.getQdwd();
//                    info[2][5] = PD.getQvwd();
//                    info[2][6] = PD.getQarf();

                    qtep.setText(PD.getQtep());
                    qhmd.setText(PD.getQhmd());
                    qapr.setText(PD.getQapr());
                    qdwd.setText(PD.getQdwd());
                    qvwd.setText(PD.getQvwd());
//                    qarf.setText(PD.getQarf());

                    break;

                default:
            }

            asyncDialog.dismiss();
        }
    }

}
