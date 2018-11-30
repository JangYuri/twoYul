package com.twoyul.myapplication.Model;

public class PowerPlant {
    private String strOrgCd;
    private String strSnumPos;
    private String plantName;

    PowerPlant(String strOrgCd, String strSnumPos, String plantName) {
        this.strOrgCd = strOrgCd;
        this.strSnumPos = strSnumPos;
        this.plantName = plantName;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getStrOrgCd() {
        return strOrgCd;
    }

    public String getStrSnumPos() {
        return strSnumPos;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public void setStrOrgCd(String strOrgCd) {
        this.strOrgCd = strOrgCd;
    }

    public void setStrSnumPos(String strSnumPos) {
        this.strSnumPos = strSnumPos;
    }
}

