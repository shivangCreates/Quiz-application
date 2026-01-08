package com.springbootapi.springbootRestApi.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attempt")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String email;

    private String collegeName;


    @Column(nullable = false)
    private String quizcode;


    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer quizDuration;


    private Integer correct = 0;
    private Integer wrong = 0;
    private Integer totalQuestions = 0;

    // IN_PROGRESS / COMPLETED
    private String status;
    private Integer quizId;

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Attempt() {
    }

    public Attempt(String name, String mobile, String email, String collegeName,
                   String quizcode, LocalDateTime startTime, Integer quizDuration,
                   String status) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.collegeName = collegeName;
        this.quizcode = quizcode;
        this.startTime = startTime;
        this.quizDuration = quizDuration;
        this.status = status;
    }

    // ===== GETTERS & SETTERS =====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getQuizcode() {
        return quizcode;
    }

    public void setQuizcode(String quizcode) {
        this.quizcode = quizcode;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getQuizDuration() {
        return quizDuration;
    }

    public void setQuizDuration(Integer quizDuration) {
        this.quizDuration = quizDuration;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getWrong() {
        return wrong;
    }

    public void setWrong(Integer wrong) {
        this.wrong = wrong;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ===== toString =====
    @Override
    public String toString() {
        return "Attempt{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", quizcode='" + quizcode + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", quizDuration=" + quizDuration +
                ", correct=" + correct +
                ", wrong=" + wrong +
                ", totalQuestions=" + totalQuestions +
                ", status='" + status + '\'' +
                '}';
    }
}
