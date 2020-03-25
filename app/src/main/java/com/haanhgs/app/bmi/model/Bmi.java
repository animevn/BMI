package com.haanhgs.app.bmi.model;

public class Bmi {

    private float height;
    private float weight;
    private Float bmi;
    private String bmiCategory = "";

    public float getHeight(){
        return height;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public float getWeight(){
        return weight;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public void calBmi(){
        this.bmi = weight*10000/(height * height);
    }

    public Float getBmi(){
        return bmi;
    }

    public void setBmi(Float bmi){
        this.bmi = bmi;
    }

    public void setBmiCategory(String bmiCategory){
        this.bmiCategory = bmiCategory;
    }

    public String getBmiCategory(){
        return bmiCategory;
    }

    public void calBmiCategory(){
        if (bmi < 15){
            this.bmiCategory = "too low";
        }else if (bmi < 18){
            this.bmiCategory = "low";
        }else if (bmi < 25){
            this.bmiCategory = "good";
        }else if (bmi < 30){
            this.bmiCategory = "overweight";
        }else if (bmi < 35){
            this.bmiCategory = "fat";
        }else {
            this.bmiCategory = "too fat";
        }
    }

}
