package com.haanhgs.app.bmi.viewmodel.view;

import com.haanhgs.app.bmi.model.Bmi;
import com.haanhgs.app.bmi.model.Repo;

import androidx.lifecycle.LiveData;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private Repo repo = new Repo();

    public LiveData<Bmi> getLiveData(){
        return repo.getLiveData();
    }

    public void setHeight(String string){
        repo.setHeight(string);
    }

    public void setWeight(String string){
        repo.setWeight(string);
    }

    public void calculateBMI(){
        repo.calculateBMI();
    }

}
