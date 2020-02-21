package com.example.sharpguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int gene_num, input_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        gene_num = rand.nextInt(9998) + 1;

        EditText input_check = (EditText)findViewById(R.id.num_guessed);
        input_check.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                EditText temp = (EditText) findViewById(R.id.num_guessed);
                Button btn_temp = findViewById(R.id.guess);
                if (temp.getText().length() != 0) {
                    btn_temp.setVisibility(0);
                }
                else {
                    btn_temp.setVisibility(8);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count,int after) {

                }

            @Override
            public void afterTextChanged(Editable edit) {

            }
        });

    }




    public void btn_guess(View view) {
        EditText temp = (EditText) findViewById(R.id.num_guessed);
        if (temp.getText().length() == 0) {
            Toast.makeText(MainActivity.this, "Please enter your guess..", Toast.LENGTH_LONG).show();
        } else {
            input_num = Integer.parseInt(temp.getText().toString().trim());
            if (input_num == gene_num) {
                setContentView(R.layout.result_right);
                TextView result;
                result = findViewById(R.id.num_generated);
                result.setText(String.valueOf(gene_num));
            }
            if (input_num > gene_num) {
                setContentView(R.layout.result_wrong_down);
                TextView num_down;
                num_down = findViewById(R.id.num_show_down);
                num_down.setText(String.valueOf(input_num));
            }
            if (input_num < gene_num) {
                setContentView(R.layout.result_wrong_up);
                TextView num_down;
                num_down = findViewById(R.id.num_show_up);
                num_down.setText(String.valueOf(input_num));
            }
        }

    }

    public void image_restart(View view) {
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        gene_num = rand.nextInt(10000);
    }

    public void image_reguess(View view) {
        setContentView(R.layout.activity_main);
    }
}
