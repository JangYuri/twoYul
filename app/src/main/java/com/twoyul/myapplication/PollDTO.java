package com.twoyul.myapplication;

public class PollDTO {
    String saupso = "-";//발전소명
    String snumpos = "-";//지역코드
    String pos = "-";//지역명
    String dddprc = "-";//시간

    //대기
    String qno2 = "-";//질소산화물
    String qso2 = "-";//황산화물
    String qpms = "-";//먼지

    //수질
    String qph = "-";//PH
    String qcod = "-";//COD
    String qss = "-";//SS

    //기상
    String qtep = "-";//온도
    String qhmd = "-";//습도
    String qapr = "-";//기압
    String qdwd = "-";//풍향
    String qvwd = "-";//풍속
    String qarf = "-";//강수량


    public String getSaupso(){
        return saupso;
    }
    public void setSaupso(String saupso){
        this.saupso = saupso;
    }

    public String getDddprc(){
        return dddprc;
    }
    public void setDddprc (String dddprc){
        this.dddprc = dddprc;
    }

    //대기
    public String getQno2(){
        return qno2;
    }
    public void setQno2(String qno2){
        this.qno2 = qno2;
    }

    public String getQso2(){
        return qso2;
    }
    public void setQso2(String qso2){
        this.qso2 = qso2;
    }

    public String getQpms(){
        return qpms;
    }
    public void setQpms(String qpms){
        this.qpms = qpms;
    }

    //수질
    public String getQph(){ return qph; }
    public void setQph(String qph){ this.qph = qph; }

    public String getQcod(){
        return qcod;
    }
    public void setQcod(String qcod){
        this.qcod = qcod;
    }

    public String getQss(){
        return qss;
    }
    public void setQss(String qss){
        this.qss = qss;
    }

    //기상
    public String getQtep(){ return qtep; }
    public void setQtep(String qtep){ this.qtep = qtep; }

    public String getQhmd(){
        return qhmd;
    }
    public void setQhmd(String qhmd){
        this.qhmd = qhmd;
    }

    public String getQapr(){ return qapr; }
    public void setQapr(String qapr){ this.qapr = qapr; }

    public String getQdwd(){ return qdwd; }
    public void setQdwd(String qdwd){ this.qdwd = qdwd; }


    public String getQvwd(){ return qvwd; }
    public void setQvwd(String qvwd){ this.qvwd = qdwd; }

    public String getQarf(){ return qarf; }
    public void setQarf(String qarf){ this.qarf = qarf; }

}