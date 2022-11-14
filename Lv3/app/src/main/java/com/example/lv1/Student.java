package com.example.lv1;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    String Name;
    String Surname;
    String BirthDate;
    int SubjectId;

    public Student(){}

    public Student(String name, String surname, String birthDate,int subjectId) {
        Name = name;
        Surname = surname;
        BirthDate = birthDate;
        SubjectId = subjectId;
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

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int subjectId) {
        SubjectId = subjectId;
    }

    public Student(Parcel in){
        String[] data= new String[4];

        in.readStringArray(data);
        this.Name= data[0];
        this.Surname= data[1];
        this.BirthDate= data[2];
        this.SubjectId= Integer.parseInt(data[3]);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{this.Name,this.Surname,this.BirthDate,String.valueOf(this.SubjectId)});
    }

    public static final Parcelable.Creator<Student> CREATOR= new Parcelable.Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new Student(source);  //using parcelable constructor
        }

        @Override
        public Student[] newArray(int size) {
// TODO Auto-generated method stub
            return new Student[size];
        }
    };

}
