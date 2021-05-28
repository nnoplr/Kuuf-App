package com.novika.novikakuuf;

import java.io.Serializable;

public class DataUser implements Serializable {

    private String username, password, phoneNumber;
    private String dateOfBirth, gender;
    private int ID, wn;

    public DataUser(String username, String password, String phoneNumber, String dateOfBirth, String gender, int ID, int wn) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.ID = ID;
        this.wn = wn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWn() {
        return wn;
    }

    public void setWn(int wn) {
        this.wn = wn;
    }
}
