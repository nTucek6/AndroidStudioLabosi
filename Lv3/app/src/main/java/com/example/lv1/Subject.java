package com.example.lv1;

import android.os.Parcel;
import android.os.Parcelable;

public class Subject implements Parcelable {
    int Id;
    String Professor;
    String Subject;
    int AcademicYear;
    int ClassesHours;
    int PracticeHours;

    public Subject(int id,String professor, String subject, int academicYear, int classesHours, int practiceHours) {
        Id = id;
        Professor = professor;
        Subject = subject;
        AcademicYear = academicYear;
        ClassesHours = classesHours;
        PracticeHours = practiceHours;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProfessor() {
        return Professor;
    }

    public void setProfessor(String professor) {
        Professor = professor;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public int getAcademicYear() {
        return AcademicYear;
    }

    public void setAcademicYear(int academicYear) {
        AcademicYear = academicYear;
    }

    public int getClassesHours() {
        return ClassesHours;
    }

    public void setClassesHours(int classesHours) {
        ClassesHours = classesHours;
    }

    public int getPracticeHours() {
        return PracticeHours;
    }

    public void setPracticeHours(int practiceHours) {
        PracticeHours = practiceHours;
    }


    public Subject(Parcel in){
        String[] data= new String[6];

        in.readStringArray(data);
        this.Id= Integer.parseInt(data[0]);
        this.Professor= data[1];
        this.Subject = data[2];
        this.AcademicYear= Integer.parseInt(data[3]);
        this.ClassesHours= Integer.parseInt(data[4]);
        this.PracticeHours= Integer.parseInt(data[5]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]
                {String.valueOf(this.Id),
                this.Professor,
                this.Subject,
                String.valueOf(this.AcademicYear),
                String.valueOf(this.ClassesHours),
                String.valueOf(this.PracticeHours)
                }
        );
    }

    public static final Parcelable.Creator<Subject> CREATOR= new Parcelable.Creator<Subject>() {

        @Override
        public Subject createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new Subject(source);  //using parcelable constructor
        }

        @Override
        public Subject[] newArray(int size) {
// TODO Auto-generated method stub
            return new Subject[size];
        }
    };
}
