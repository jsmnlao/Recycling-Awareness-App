package edu.sjsu.android.project1jasminelao;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.sjsu.android.project1jasminelao.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize binding variable
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // use binding to refer to any widgets in layout file
        setContentView(binding.getRoot());

        binding.calBtn.setOnClickListener(this::onClick);
        binding.unBtn.setOnClickListener(this::uninstall);
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("DefaultLocale")
            @Override
            // when progress change, text should change
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.rateText.setText(String.format("Interest Rate: %.1f", progress / 10.0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); // set view of activity to layout file
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @SuppressLint("DefaultLocale")
    private void onClick(View v){
        String principle = binding.input.getText().toString(); // grab the input value from user
        if(!isValid(principle)){
            // TODO: toast a message
            return;
        }
        double P = Double.parseDouble(principle); // convert string to double
        double J = binding.seekBar.getProgress() / 10.0 / 12 / 100; // getting the percentage
        int N = getMonths();
        double T = binding.checkBox.isChecked() ? 0.1 / 100 * P : 0.0;
        double result = Calculator.calculate(P, J, N, T);
        binding.resultText.setText(String.format("Monthly payment: %.2f", result));

    }

    /**
     * Ensure user input is valid.
     * @param input
     * @return
     */
    private boolean isValid(String input){
        // TODO: input should be non-empty and 2 decimal places
        return false;
    }

    private int getMonths(){
        if(binding.r15.isChecked()){
            return 15 * 12;
        }
        else if(binding.r20.isChecked()){
            return 20 * 12;
        }
        else{
            return 30 * 12;
        }
    }

    private void uninstall(View v){
        // TODO: code given in instruction
    }
}