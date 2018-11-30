package com.twoyul.myapplication.Model;

import java.util.ArrayList;

public class PowerPlants {
    private static final ArrayList<PowerPlant> ourInstance = new ArrayList<>();

    public static ArrayList<PowerPlant> getInstance() {
        ourInstance.add(new PowerPlant("875", "1", "신인천복합화력발전소 "));
        ourInstance.add(new PowerPlant("876", "1", "삼척그린파워발전소"));
        ourInstance.add(new PowerPlant("876", "1", "안동복합화력발전소"));
        ourInstance.add(new PowerPlant("876", "1", "영월복합화력발전소"));
        ourInstance.add(new PowerPlant("209", "1", "부산복합화력발전소"));
        ourInstance.add(new PowerPlant("862", "2", "하동화력발전소"));
        ourInstance.add(new PowerPlant("855", "1", "남제주화력발전소"));

        return ourInstance;
    }
}
