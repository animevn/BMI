package com.haanhgs.app.bmi.view;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.haanhgs.app.bmi.R;
import com.haanhgs.app.bmi.viewmodel.view.ViewModel;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getLiveData().observe(this, bmi -> {
            if (bmi.getBmi() > 0){
                tvBMI.setText(String.format(Locale.getDefault(), "%.2f", bmi.getBmi()));
            } else {
                tvBMI.setText("");
            }
            tvResult.setText(bmi.getBmiCategory());
        });
    }

    @OnClick(R.id.bnCalculate)
    public void onViewClicked() {
        hideKeboard();
        viewModel.setHeight(etHeight.getText().toString().trim());
        viewModel.setWeight(etWeight.getText().toString().trim());
        viewModel.calculateBMI();
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

}
