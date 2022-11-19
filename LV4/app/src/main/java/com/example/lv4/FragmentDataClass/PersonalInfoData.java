package com.example.lv4.FragmentDataClass;

public class PersonalInfoData {

    private String Name;
    private String Surname;
    private String BirthDate;
    private String ImageBase64;


    public PersonalInfoData(String name, String surname, String birthDate,String imageBase64) {
        Name = name;
        Surname = surname;
        BirthDate = birthDate;
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

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getImageBase64() {
        return ImageBase64;
    }

    public void setImageBase64(String imageBase64) {
        ImageBase64 = imageBase64;
    }

    public boolean CheckEmpty()
    {
        if(getName().length() != 0 && getSurname().length() != 0 && getBirthDate().length() != 0 && getImageBase64().length() != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
