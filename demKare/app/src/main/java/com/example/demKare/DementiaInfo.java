package com.example.demKare;

public class DementiaInfo {
    public String levelOne(){
        return "one";
    }
    public String levelTwo(){
        return "two";
    }

    public String levelThree(){
        return "three";
    }

    public String informationAboutDementia(int level){
        switch (level) {
            case 1:
                return levelOne();
            case 2:
                return levelTwo();
            case 3:
                return levelThree();
        }
        return null;
    }
}
