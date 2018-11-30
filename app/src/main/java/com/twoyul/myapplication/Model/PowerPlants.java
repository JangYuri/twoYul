package com.twoyul.myapplication.Model;

import java.util.ArrayList;

public class PowerPlants {
    private static final ArrayList<PowerPlant> ourInstance = new ArrayList<>();

    public static ArrayList<PowerPlant> getInstance() {
        ourInstance.add(new PowerPlant("209", "1", "부산 발전소"));
        ourInstance.add(new PowerPlant("209", "2", "부산 동아대학"));
        ourInstance.add(new PowerPlant("209", "4", "부산 다대동사무소"));
        ourInstance.add(new PowerPlant("855", "1", "남제주 발전소"));
        ourInstance.add(new PowerPlant("855", "3", "남제주 회순리"));
        ourInstance.add(new PowerPlant("862", "2", "하동 궁항리"));

        return ourInstance;
    }
}
