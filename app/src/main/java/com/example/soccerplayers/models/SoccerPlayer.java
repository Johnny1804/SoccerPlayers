package com.example.soccerplayers.models;

import java.io.Serializable;
import java.util.Calendar;

public class SoccerPlayer implements Serializable {

    // attributes

    private String name;
    private String birth;
    private String age;
    private String height;
    private String citizenship;
    private String position;
    private String currentClub;
    private String image;
    private String infoURL;

    // constructors

    public SoccerPlayer(String name, String birth, String height, String citizenship, String position, String currentClub, String image, String infoURL) {
        this.name = name;
        this.birth = birth;
        this.height = height;

        try {
            this.age = calculateAge(birth);
        } catch (Exception e) {
            this.age = "";
        }

        this.citizenship = citizenship;
        this.position = position;
        this.currentClub = currentClub;
        this.image = image;
        this.infoURL = infoURL;
    }

    public SoccerPlayer() {
        this.name = "";
        this.birth = "";
        this.age = "";
        this.citizenship = "";
        this.position = "";
        this.currentClub = "";
        this.image = "";
        this.infoURL = "";
    }

    private String calculateAge(String birth1){
        String[] parts = birth1.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        Calendar birthDay = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        birthDay.set(year, month, day);

        int calculatedAge = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)){
            calculatedAge--;
        }

        return String.valueOf(calculatedAge);
    }

    // getters

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getHeight() {
        return height;
    }

    public String getAge() {
        return age;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getPosition() {
        return position;
    }

    public String getCurrentClub() {
        return currentClub;
    }

    public String getImage() {
        return image;
    }

    public String getInfoURL() {
        return infoURL;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }

}
