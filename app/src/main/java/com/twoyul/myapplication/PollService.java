package com.twoyul.myapplication;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PollService {
    public static PollDTO getData(String str) {
        PollDTO PD = new PollDTO();
        String data = str;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parse = factory.newPullParser();
            parse.setInput(new StringReader(data));

            int type = parse.getEventType();
            boolean flag = false;
            String local = "";
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        local = parse.getName();
                        if (local.equals("saupso")
                                || local.equals("dddprc")
                                || local.equals("qno2")
                                || local.equals("qso2")
                                || local.equals("qpms")
                                || local.equals("qph")
                                || local.equals("qcod")
                                || local.equals("qss")
                                || local.equals("qtep")
                                || local.equals("qhmd")
                                || local.equals("qapr")
                                || local.equals("qdwd")
                                || local.equals("qvwd")
                                || local.equals("qarf")){
                            flag = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (flag) {
                            if (local.equals("saupso")) {
                                PD.setSaupso(parse.getText());
                            } else if (local.equals("dddprc")) {
                                PD.setDddprc(parse.getText());
                            } else if (local.equals("qno2")) {
                                PD.setQno2(parse.getText());
                            } else if (local.equals("qso2")) {
                                PD.setQso2(parse.getText());
                            } else if (local.equals("qpms")) {
                                PD.setQpms(parse.getText());
                            } else if (local.equals("qph")) {
                                PD.setQph(parse.getText());
                             } else if (local.equals("qcod")) {
                                PD.setQcod(parse.getText());
                             } else if (local.equals("qss")) {
                                PD.setQss(parse.getText());
                            } else if (local.equals("qtep")) {
                                PD.setQtep(parse.getText());
                            } else if (local.equals("qhmd")) {
                                PD.setQhmd(parse.getText());
                            } else if (local.equals("qapr")) {
                                PD.setQapr(parse.getText());
                            } else if (local.equals("qdwd")) {
                                PD.setQdwd(parse.getText());
                            } else if (local.equals("qvwd")) {
                                PD.setQvwd(parse.getText());
                            } else if (local.equals("qarf")) {
                                PD.setQarf(parse.getText());
                            }




                        flag = false;
                        }
                        break;


                    default:
                        break;
                }
                type = parse.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return PD;

    }
}
