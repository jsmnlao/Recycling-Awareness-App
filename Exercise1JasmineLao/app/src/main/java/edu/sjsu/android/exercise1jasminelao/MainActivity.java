package edu.sjsu.android.exercise1jasminelao;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.sjsu.android.exercise1jasminelao.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Steps to use view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set onClickListener to the button and pass onClick method
        binding.button.setOnClickListener(this::onClick);
    }

    /**
     * Prompted when "Calculate" button is clicked. The textbox will show the
     * result of conversion and option is switched to the other one.
     * @param view
     */
    public void onClick(View view){
        // Toast a message for empty entry
        // Toast: view containing a quick little message for the user
        if(binding.textView.getText().length() == 0){
          Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
          System.out.println(binding.textView.getText());
          return;
        }

        // Get input value from the text box
        float inputValue = Float.parseFloat(binding.textView.getText().toString());
        if(binding.radioButton.isChecked()){
            binding.textView.setText(String.valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
            // Switch to the other option
            binding.radioButton.setChecked(false);
            binding.radioButton2.setChecked(true);
        }
        else{
            binding.textView.setText(String.valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
            binding.radioButton.setChecked(true);
            binding.radioButton2.setChecked(false);
        }
    }
}