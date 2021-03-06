package kth.jjve.xfran.models;

/*
Function: defines result object
Activity:
Jitse van Esch, Elisa Perini & Mariah Sabioni
 */

public class Result {

    private Workout workout;
    private String scoreType, comments, score, wodName; //Todo: deal better with different types of scores
    private Integer feelScore;
    private String date;
    private boolean scaled, empty;

    public Result(Workout workout, String wodName, String score, Integer feelScore, String comments, String date, boolean scaled) {
        this.workout = workout;
        this.wodName = wodName;
        this.score = score;
        this.feelScore = feelScore;
        this.comments = comments;
        this.date = date;
        this.scaled = scaled;
    }

    public Result() {
    }

    public Workout getWorkout() {
        return workout;
    }

    public String getWodName() {
        return wodName;
    }

    public String getDate() {
        return date;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getFeelScore() {
        return feelScore;
    }

    public void setFeelScore(Integer feelScore) {
        this.feelScore = feelScore;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isScaled() {
        return scaled;
    }

    public void setScaled(boolean scaled) {
        this.scaled = scaled;
    }

}
