package com.example.testevaluation;

public class Assessment {
    public String questions[];
    public String answers[];
    public int score;
    public boolean answered;

    public String[] getQuestions(){
        return this.questions;
    }

    public void setQuestions(String[] questionsGiven){
        this.questions=questionsGiven;
    }

    public String[] getAnswers(){
        return this.answers;
    }

    public void setAnswers(String[] answersGiven){
        this.answers=answersGiven;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int value){
        this.score=value;
    }

    public Assessment(String[] questionsGiven,String[] answersGiven, int score){
        this.questions=questionsGiven;
        this.answers=answersGiven;
        this.score=score;
    }
    public Assessment(int num){
        this.questions= new String[num];
        this.answers= new String[num];
        this.score=0;
    }

    public boolean isAnswered(){
        return this.answered;
    }

    public void setAnswered(boolean value){
        this.answered=value;
    }
}
