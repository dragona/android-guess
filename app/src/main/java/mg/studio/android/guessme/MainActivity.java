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

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    Button button;
    EditText et_input;
    int random_guess;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random_guess = generatorGuess();

        // constantly check the user input, if [1-1000], set
        // button visible
        button = findViewById(R.id.btn_check);
        display = findViewById(R.id.tv_info);
        et_input = findViewById(R.id.et_input);
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("TAG", "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("TAG", "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // ensure user input is [1 - 1000]
                String input = et_input.getText().toString();
                Log.e("TAG", input);
                if (!input.isEmpty() && Integer.valueOf(input) > 0 && Integer.valueOf(input) <= 1000) {
                    // Set the button visible
                    button.setVisibility(View.VISIBLE);
                    display.setVisibility(View.GONE);
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
        int user_input = Integer.valueOf(et_input.getText().toString());
        if (compare(user_input) > 0) {
            Toast.makeText(this, "The hidden value is greater than " + user_input, Toast.LENGTH_LONG).show();
        } else if (compare(user_input) < 0) {
            Toast.makeText(this, "The hidden value is smaller than " + user_input, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You got it ! \n" + user_input, Toast.LENGTH_LONG).show();
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
}
