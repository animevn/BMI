package com.haanhgs.app.bmi.viewmodel.view;

import android.app.Application;
import com.haanhgs.app.bmi.model.Bmi;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends AndroidViewModel {

    private MutableLiveData<Bmi> liveData = new MutableLiveData<>();

    public ViewModel(@NonNull Application application) {
        super(application);
        liveData.setValue(new Bmi());
    }

    public MutableLiveData<Bmi> getLiveData() {
        return liveData;
    }

    private static boolean isFloat(String string){
        try{
            return Float.parseFloat(string) > 0;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public void calculateBmi(String weightStr, String heightStr) {
        if (liveData.getValue() != null){
            Bmi bmi = liveData.getValue();
            if (isFloat(weightStr) && isFloat(heightStr)){
                float weight = Float.parseFloat(weightStr);
                float height = Float.parseFloat(heightStr);
                bmi.setHeight(height);
                bmi.setWeight(weight);
                bmi.calBmi();
                bmi.calBmiCategory();
            }else {
                bmi.setBmi(null);
                bmi.setBmiCategory("Weight and Height cannot be zero");
            }
            liveData.setValue(bmi);
        }
    }

}
