package com.springbootapi.springbootRestApi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String quizName;
    @Column
    private String quizDescription;
    @Column
    private String quizCode;
    @Column
    private int quizTime;
    @Column
    private int quesNumber;

    public  Quiz(){

    }

    public Quiz(int quesNumber, String quizCode, String quizDescription, String quizName, int quizTime) {
        this.quesNumber = quesNumber;
        this.quizCode = quizCode;
        this.quizDescription = quizDescription;
        this.quizName = quizName;
        this.quizTime = quizTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizCode() {
        return quizCode;
    }

    public void setQuizCode(String quizCode) {
        this.quizCode = quizCode;
    }


    public int getQuesNumber() {
        return quesNumber;
    }

    public void setQuesNumber(int quesNumber) {
        this.quesNumber = quesNumber;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getQuizTime() {
        return quizTime;
    }

    public void setQuizTime(int quizTime) {
        this.quizTime = quizTime;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", quizName='" + quizName + '\'' +
                ", quizDescription='" + quizDescription + '\'' +
                ", quizCode=" + quizCode +
                ", quizTime=" + quizTime +
                ", quesNumber=" + quesNumber +
                '}';
    }
}
