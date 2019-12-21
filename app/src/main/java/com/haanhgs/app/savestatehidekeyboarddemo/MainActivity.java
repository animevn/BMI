package com.haanhgs.app.savestatehidekeyboarddemo;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.etWeight)
    EditText etWeight;
    @BindView(R.id.etHeight)
    EditText etHeight;
    @BindView(R.id.tvBMI)
    TextView tvBMI;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.bnCalculate)
    Button bnCalculate;

    private float weight;
    private float height;
    private float bmi;

    private static final String BMI = "bmi";
    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String MESSAGE = "message";

    private void loadSavedInstance(Bundle bundle){
        if (bundle != null){
            weight = bundle.getFloat(WEIGHT);
            height = bundle.getFloat(HEIGHT);
            bmi = bundle.getFloat(BMI);
            String message = bundle.getString(MESSAGE);

            if (weight > 0) etWeight.setText(String.format("%s", weight));
            if (height > 0) etHeight.setText(String.format("%s", height));
            if (bmi > 0) tvBMI.setText(String.format(Locale.getDefault(), "%.1f", bmi));
            if (message != null) tvResult.setText(message);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadSavedInstance(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MESSAGE, tvResult.getText().toString());
        outState.putFloat(WEIGHT, weight);
        outState.putFloat(HEIGHT, height);
        outState.putFloat(BMI, bmi);
    }

    private boolean isFloat(String string){
        try{
            return Float.parseFloat(string) > 0;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private String bmiCat(){
        if (bmi < 15){
            return getString(R.string.too_low);
        }else if (bmi < 18){
            return getString(R.string.low);
        }else if (bmi < 25){
            return getString(R.string.ok);
        }else if (bmi < 30){
            return getString(R.string.overweight);
        }else if (bmi < 35){
            return getString(R.string.fat);
        }else {
            return getString(R.string.too_fat);
        }
    }

    private void hideKeboard(){
        InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (manager != null && view != null){
            manager.hideSoftInputFromWindow(
                    view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void calculateBMI(){
        if (isFloat(etHeight.getText().toString()) && isFloat(etWeight.getText().toString())){
            hideKeboard();
            weight = Float.parseFloat(etWeight.getText().toString());
            height = Float.parseFloat(etHeight.getText().toString());
            bmi = weight*10000/(height * height);
            tvBMI.setText(String.format(Locale.getDefault(), "%.1f", bmi));
            tvResult.setText(bmiCat());
        }
    }

    @OnClick(R.id.bnCalculate)
    public void onViewClicked() {
        calculateBMI();
    }
}
