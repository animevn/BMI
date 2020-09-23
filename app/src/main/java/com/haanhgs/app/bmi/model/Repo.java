package com.haanhgs.app.bmi.model;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import androidx.lifecycle.MutableLiveData;

public class Repo {

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 8, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    private Bmi bmi = new Bmi();
    private MutableLiveData<Bmi> bmiLiveData = new MutableLiveData<>();

    public Repo() {
        bmiLiveData.setValue(bmi);
    }

    public MutableLiveData<Bmi> getLiveData() {
        return bmiLiveData;
    }

    public void setHeight(String string){
        if (isFloat(string)){
            bmi.setHeight(Float.parseFloat(string));
        }
    }

    public void setWeight(String string){
        if (isFloat(string)){
            bmi.setWeight(Float.parseFloat(string));
        }
    }

    public void calculateBMI(){
        float height = bmi.getHeight();
        float weight = bmi.getWeight();

        executor.execute(() -> {
            if (height > 0 && weight > 0){
                bmi.setBmi(weight*10000/(height*height));
                bmi.setBmiCategory(getBmiCategory(bmi.getBmi()));
            }else {
                bmi.setBmi(0);
                bmi.setBmiCategory("Weight and Height cannot be zero");
            }
            bmiLiveData.postValue(bmi);
        });

    }

    private static String getBmiCategory(float bmi){
        if (bmi < 15){
            return "too low";
        }else if (bmi < 18){
            return "low";
        }else if (bmi < 25){
            return "good";
        }else if (bmi < 30){
            return "overweight";
        }else if (bmi < 35){
            return "fat";
        }else {
            return "too fat";
        }
    }

    private static boolean isFloat(String string){
        try{
            return Float.parseFloat(string) > 0;
        }catch (NumberFormatException e){
            return false;
        }
    }

}
