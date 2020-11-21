package com.example.testevaluation;

public class DementiaInfo {
    public String levelOne(){
        return "one";
    }
    public String levelTow(){
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
                 return levelTow();
            case 3:
                return levelThree();
        }
        return null;
    }
}
