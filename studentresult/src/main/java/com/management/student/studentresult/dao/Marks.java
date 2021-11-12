package com.management.student.studentresult.dao;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Marks")

public class Marks {

    @Id
    @GeneratedValue
    @Column(name = "mrk_id")
    private int mrkId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "sub_id")
    private Subject subject;
    private int score;
    @Column(name = "tot_score")
    private int totScore;
    private int year;
    private int term;
    private String grade;
    private String status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_at")
    private Date modifiedAt;

    @PrePersist
    void created_at() {
        this.status = "ACTIVE";
        this.createdAt = this.modifiedAt = new Date();
    }

    @PreUpdate
    void modified_at() {
        this.modifiedAt = new Date();
    }

    public Marks(User user, Subject subject, int score, int totScore, int year, int term, String grade) {
        this.user = user;
        this.subject = subject;
        this.score = score;
        this.totScore = totScore;
        this.year = year;
        this.term = term;
        this.grade = grade;
        this.status = "ACTIVE";
    }

    public Marks(User user, int year, int term) {
        this.user = user;
        this.year = year;
        this.term = term;
    }

    public Marks() {
    }

    public int getMrkId() {
        return mrkId;
    }

    public void setMrkId(int mrkId) {
        this.mrkId = mrkId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotScore() {
        return totScore;
    }

    public void setTotScore(int totScore) {
        this.totScore = totScore;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


}
