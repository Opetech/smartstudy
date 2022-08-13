package com.techwiz.smartstudy.model;

public class ScoreDetails {
    private int id;
    private int testId;
    private int userId;
    private String description;
    private String date;
    private int score;

    public ScoreDetails(int testId, int userId, String description, String date, int score) {
        this.testId = testId;
        this.userId = userId;
        this.description = description;
        this.date = date;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
