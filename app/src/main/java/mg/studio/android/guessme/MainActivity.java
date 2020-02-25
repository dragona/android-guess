/*
Random number guessing game: The computer thinks of a number from 1 to 1000.
The user makes guesses, and after each incorrect guess, the app hints to the
user whether the right answer is higher or lower than their guess. The game
ends when the player guesses the right number.

Todo 1: get the user input
Todo 2: ensure user input is > 0 and <= 1000
Todo 3: enable the button check when the user input is valid

 */

package mg.studio.android.guessme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    int random_guess;
    TextView display, finalDisplay, tvRestart;
    ImageView imgDown, imgUp, imgMood;
    private int checkedUserInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define the number to guess
        random_guess = generatorGuess();

        // connect the images
        imgDown = findViewById(R.id.img_arrow_down);
        imgUp = findViewById(R.id.img_arrow_up);
        imgMood = findViewById(R.id.img_mood);

        // set the images visibility
        imgUp.setVisibility(View.INVISIBLE);
        imgDown.setVisibility(View.INVISIBLE);
        imgMood.setVisibility(View.INVISIBLE);

        // constantly check the user input, if [1-1000], set
        // button visible
        button = findViewById(R.id.btn_check);
        display = findViewById(R.id.tv_info);
        finalDisplay = findViewById(R.id.tv_final_display);
        tvRestart = findViewById(R.id.tv_restart);
        editText = findViewById(R.id.et_input);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("TAG", "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("TAG", "onTextChanged");
                imgVisibility(2);
                hideKeyboard();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // ensure that the user input is [1 - 1000]
                String input = editText.getText().toString();
                display.setTextColor(getApplicationContext().getColor(R.color.colorAccent));
                if (input.isEmpty()) {
                    imgVisibility(2); // images set invisible
                }
                if (!input.isEmpty() && isNumeric(input) && Integer.valueOf(input) > 0 && Integer.valueOf(input) <= 1000) {
                    // Set the button visible
                    button.setVisibility(View.VISIBLE);
                    display.setVisibility(View.INVISIBLE);
                } else {
                    button.setVisibility(View.INVISIBLE);
                    // inform the user about the valid inputs
                    display.setText("The computer only considers a number from 1 to 1000.");
                    display.setVisibility(View.VISIBLE);
                }


            }
        });

    }

    private boolean isNumeric(String input) {
        try {
            int value = Integer.valueOf(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Compare the int user_input and the generated int and
     * return the difference. A value 0 would mean that the
     * two values are the same
     *
     * @return int value that corresponds to the difference between
     * the user input and the generated int
     */
    private int compare(int user_input) {
        return random_guess - user_input;
    }

    public void btnCheck(View view) {
        button.setVisibility(View.GONE);
        checkedUserInput = Integer.valueOf(editText.getText().toString());
        if (compare(checkedUserInput) > 0) {
            imgVisibility(-1);
        } else if (compare(checkedUserInput) < 0) {
            imgVisibility(1);
        } else {
            imgVisibility(0);
        }
    }


    /**
     * Each time the app is started (when the activity is started/ restarted)
     * a new random number between [1 - 1000] should be generated
     *
     * @return int value [1 - 1000]
     */
    private int generatorGuess() {
        //todo : implement the random number generator
        return 345;
    }

    /**
     * Specifies the views visibility
     *
     * @param value defines the status
     */
    private void imgVisibility(int value) {
        if (value == -1) {
            // value entered too low
            imgDown.setBackgroundResource(R.drawable.bg_down_arrow_grey);
            imgUp.setBackgroundResource(R.drawable.bg_up_arrow_color);
            imgMood.setBackgroundResource(R.drawable.fail); // mood fail
            imgDown.setVisibility(View.VISIBLE);
            imgUp.setVisibility(View.VISIBLE);
            imgMood.setVisibility(View.VISIBLE);
            display.setText("The right answer is higher than your guess.");
            display.setVisibility(View.VISIBLE);
            tvRestart.setVisibility(View.GONE);
        } else if (value == 1) {
            // value entered too high
            imgDown.setBackgroundResource(R.drawable.bg_down_arrow_color);
            imgUp.setBackgroundResource(R.drawable.bg_up_arrow_grey);
            imgMood.setBackgroundResource(R.drawable.fail);
            imgDown.setVisibility(View.VISIBLE);
            imgUp.setVisibility(View.VISIBLE);
            imgMood.setVisibility(View.VISIBLE);
            display.setText("The right answer is lower than your guess.");
            display.setVisibility(View.VISIBLE);
            tvRestart.setVisibility(View.GONE);
        } else if (value == 0) {
            // Win state
            imgDown.setVisibility(View.INVISIBLE);
            imgUp.setVisibility(View.INVISIBLE);
            imgMood.setBackgroundResource(R.drawable.win);
            imgMood.setVisibility(View.VISIBLE);
            display.setText("You got it.");
            display.setTextColor(this.getColor(R.color.colorPrimaryDark));
            display.setVisibility(View.VISIBLE);
            editText.setVisibility(View.GONE);
            finalDisplay.setText(String.valueOf(checkedUserInput));
            finalDisplay.setVisibility(View.VISIBLE);
            tvRestart.setVisibility(View.VISIBLE);
        } else {
            imgDown.setVisibility(View.INVISIBLE);
            imgUp.setVisibility(View.INVISIBLE);
            imgMood.setBackgroundResource(R.drawable.fail);
            imgMood.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Increase the value in the editText by one unless maximum is reached
     *
     * @param view
     */
    public void btnUp(View view) {
        String value = editText.getText().toString();
        if (!value.isEmpty() && Integer.valueOf(value) < 1000) {
            editText.setText(String.valueOf(Integer.valueOf(value) + 1));
        }
    }

    /**
     * Decrease the value in the editText by one unless minimum is reached
     *
     * @param view
     */
    public void btnDown(View view) {
        String value = editText.getText().toString();
        if (!value.isEmpty() && Integer.valueOf(value) > 1) {
            editText.setText(String.valueOf(Integer.valueOf(value) - 1));
        }
    }

    /**
     * Hide the virtual/soft keyboard when the button is pressed
     */
    private void hideKeyboard() {
        // Hide the keyboard after when the button is pressed
        try {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.e("TAG", "Error: hide keyboard -" + e);
        }
    }

    public void btnRestart(View view) {
        // initialize the views
        editText.setText("");
        this.recreate();
    }
}
