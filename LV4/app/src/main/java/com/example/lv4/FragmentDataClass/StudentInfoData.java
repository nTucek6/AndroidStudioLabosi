package com.example.lv4.FragmentDataClass;

public class StudentInfoData {

    private String ProfessorName;
    private String Subject;
    private int AcademicYear;
    private int ClassesHours;
    private int PracticeHours;

    public StudentInfoData(String professorName, String subject, int academicYear, int classesHours, int practiceHours) {
        ProfessorName = professorName;
        Subject = subject;
        AcademicYear = academicYear;
        ClassesHours = classesHours;
        PracticeHours = practiceHours;
    }

    public String getProfessorName() {
        return ProfessorName;
    }

    public void setProfessorName(String professorName) {
        ProfessorName = professorName;
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


    public boolean CheckEmpty()
    {
        if(getProfessorName().length() != 0 && getSubject().length() != 0 && getAcademicYear() != 0 && getClassesHours() != 0 && getPracticeHours() != 0)
        {
            return true;
        }
        else
        {
            return false;
        }


    }

}
