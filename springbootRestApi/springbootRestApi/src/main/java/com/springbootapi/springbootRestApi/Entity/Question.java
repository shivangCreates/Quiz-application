package com.springbootapi.springbootRestApi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quizid;
    @Column
    private String question;
    @Column
    private String optionA;
    @Column
    private String optionB;
    @Column
    private String optionC;
    @Column
    private String optionD;
    @Column
    private String correctAns;

    public Question() {
    }

    public Question(String question,Integer quizid, String optionA, String optionB, String optionC, String optionD, String correctAns) {
        this.question = question;
        this.optionA = optionA;
        this.quizid=quizid;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAns = correctAns;
    }


    public Integer getQuizid() {
        return quizid;
    }

    public void setQuizid(Integer quizid) {
        this.quizid = quizid;
    }

    // 🔥 Mandatory Getter + Setter for ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    @Override
    public String toString() {
        return "Question{" +
                "quizid=" + quizid +
                ", question='" + question + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", correctAns='" + correctAns + '\'' +
                '}';
    }
}
