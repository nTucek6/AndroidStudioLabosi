package com.example.lv4.Class;

public class SaveSummary {

    String Name;
    String Surname;
    String Subject;
    String ImageBase64;

    public SaveSummary(String name, String surname, String subject,String imageBase64) {
        Name = name;
        Surname = surname;
        Subject = subject;
        ImageBase64 = imageBase64;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getImageBase64() {
        return ImageBase64;
    }

    public void setImageBase64(String imageBase64) {
        ImageBase64 = imageBase64;
    }
}
