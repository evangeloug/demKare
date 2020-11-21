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

    public void informationAboutDementia(int level){
        switch (level) {
            case 1:
                levelOne();
                break;
            case 2:
                levelTow();
                break;
            case 3:
                levelThree();
                break;
        }
    }
}
