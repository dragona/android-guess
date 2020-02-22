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

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    int random_guess;
    TextView display;
    ImageView imgDown, imgUp, imgMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random_guess = generatorGuess();

        // Connect the images
        imgDown = findViewById(R.id.img_arrow_down);
        imgUp = findViewById(R.id.img_arrow_up);
        imgMood = findViewById(R.id.img_mood);


        imgUp.setVisibility(View.INVISIBLE);
        imgDown.setVisibility(View.INVISIBLE);
        imgMood.setVisibility(View.INVISIBLE);

        // constantly check the user input, if [1-1000], set
        // button visible
        button = findViewById(R.id.btn_check);
        display = findViewById(R.id.tv_info);
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
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // ensure that the user input is [1 - 1000]
                String input = editText.getText().toString();
                if (input.isEmpty()) {
                    imgVisibility(2); // images set invisible
                }
                if (!input.isEmpty() && Integer.valueOf(input) > 0 && Integer.valueOf(input) <= 1000) {
                    // Set the button visible
                    button.setVisibility(View.VISIBLE);
                    display.setVisibility(View.INVISIBLE);
                } else {
                    button.setVisibility(View.INVISIBLE);
                    // inform the user about the valid inputs
                    display.setVisibility(View.VISIBLE);


                }


            }
        });

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
        int user_input = Integer.valueOf(editText.getText().toString());
        if (compare(user_input) > 0) {
            imgVisibility(-1);
        } else if (compare(user_input) < 0) {
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
        //todo : implement the real generator
        return 345;
    }

    private void imgVisibility(int value) {
        if (value == -1) {
            // value entered too low
            imgDown.setBackgroundResource(R.drawable.down_grey); // down colored
            imgUp.setBackgroundResource(R.drawable.up_color); // down colored
            imgMood.setBackgroundResource(R.drawable.fail); // mood fail
            imgDown.setVisibility(View.VISIBLE);
            imgUp.setVisibility(View.VISIBLE);
            imgMood.setVisibility(View.VISIBLE);
        } else if (value == 1) {
            // value entered too high
            imgDown.setBackgroundResource(R.drawable.down_color);
            imgUp.setBackgroundResource(R.drawable.up_grey);
            imgMood.setBackgroundResource(R.drawable.fail);
            imgDown.setVisibility(View.VISIBLE);
            imgUp.setVisibility(View.VISIBLE);
            imgMood.setVisibility(View.VISIBLE);
        } else if (value == 0) {
            // Win state
            imgDown.setVisibility(View.INVISIBLE);
            imgUp.setVisibility(View.INVISIBLE);
            imgMood.setBackgroundResource(R.drawable.win);
            imgMood.setVisibility(View.VISIBLE);
        } else {
            imgDown.setVisibility(View.INVISIBLE);
            imgUp.setVisibility(View.INVISIBLE);
            imgMood.setBackgroundResource(R.drawable.fail);
            imgMood.setVisibility(View.INVISIBLE);
        }
    }
}
