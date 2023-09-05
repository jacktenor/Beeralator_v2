package com.sativa.beeralator_v2;
import static android.graphics.Color.parseColor;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText inputEditText;
    private Button submitButton;
    private TextView resultTextView;
    private double guests = 0;
    private double hours = 0;
    private double beersPerHour = 0;
    private double averagePricePerCase = 0;
    private double doorfee = 0;
    private int currentStep = 1;
    private Button restartButton;
    private VideoView videoView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        submitButton = findViewById(R.id.submitButton);
        restartButton = findViewById(R.id.restartButton);
        resultTextView = findViewById(R.id.resultTextView);
        SwitchMaterial switchToggle = findViewById(R.id.switchToggle);

        inputEditText.requestFocus();

            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

            askNextQuestion();
            submitButton.setOnClickListener(v -> processInput());

            videoView = findViewById(R.id.videoView);

            // Set video source from the raw directory
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.newtry);

            switchToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Play video when switch is turned on
                    videoView.start();
                    videoView.setVisibility(View.VISIBLE);

                } else {
                    // Pause and rewind video when switch is turned off
                    videoView.pause();
                    videoView.seekTo(0);
                    videoView.setVisibility(View.INVISIBLE);
                }
            });

        videoView.setOnPreparedListener(mp -> mp.setLooping(true));

        inputEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                try {
                    processInput();
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    Toast.makeText(MainActivity.this, "Please enter a number.", Toast.LENGTH_LONG).show();
                }
                return true; // Prevent the keyboard from closing
            }
            return false;
        });

        submitButton.setOnClickListener(v -> {
            try {
                processInput();
            } catch (NumberFormatException e) {
                // Handle invalid input
                Toast.makeText(MainActivity.this, "Please enter a number.", Toast.LENGTH_LONG).show();
            }
        });

        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        });
    }

    private void askNextQuestion() {

        switch (currentStep) {
            case 1 -> inputEditText.setHint("Cover charge?");
            case 2 -> inputEditText.setHint("Number of guests?");
            case 3 -> inputEditText.setHint("Hours you'll drink?");
            case 4 -> inputEditText.setHint("Beers per hour?");
            case 5 -> inputEditText.setHint("Price per case?");
        }

        inputEditText.setText("");
        currentStep++;
    }

    private void processInput() {
        String inputStr = inputEditText.getText().toString();
        double input = Double.parseDouble(inputStr);
        final MediaPlayer[] mp = {MediaPlayer.create(MainActivity.this, R.raw.reggaepaety)};

        switch (currentStep - 1) {
            case 1 -> {
                doorfee = input;
                if (doorfee >= 1 && doorfee <= 4) {
                    Toast toast = Toast.makeText(getApplicationContext(), "What a strange cover charge.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (doorfee == 5) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Ain't no reggae party, $5 at the door.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                    if (mp[0] != null) {
                        if (mp[0].isPlaying()) {
                            mp[0].stop();
                            mp[0].release();
                        }
                        mp[0] = MediaPlayer.create(MainActivity.this, R.raw.reggaepaety);
                        mp[0].start();
                    }
                }
                if (doorfee >= 6 && doorfee <= 11) {
                    Toast toast = Toast.makeText(getApplicationContext(), "A reasonable cover", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (doorfee >= 12 && doorfee <= 21) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Make them wish they had gone B.Y.O.B.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (doorfee >= 22) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "What is this a fund raiser?", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
            }
            case 2 -> {
                guests = input;
                if (guests >= 1 && guests <= 11) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No friends?", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (guests >= 12 && guests <= 49) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "A gathering!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                    if (mp[0] != null) {
                        if (mp[0].isPlaying()) {
                            mp[0].stop();
                            mp[0].release();
                        }
                        mp[0] = MediaPlayer.create(MainActivity.this, R.raw.party);
                        mp[0].start();
                    }
                }
                if (guests >= 50 && guests <= 99) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Now that's a party!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (guests >= 100 && guests <= 999) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Good old fashion rager!!!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (guests >= 1000) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Call in the National Guard!!!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
            }
            case 3 -> {
                hours = input;
                if (hours >= 1 && hours <= 3) {
                    Toast toast = Toast.makeText(getApplicationContext(), "What is this a brunch?", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (hours >= 4 && hours <= 5) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "A nice steady pace.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (hours >= 6 && hours <= 7) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Enough time to get good and loose.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                    if (mp[0] != null) {
                        if (mp[0].isPlaying()) {
                            mp[0].stop();
                            mp[0].release();
                        }
                        mp[0] = MediaPlayer.create(MainActivity.this, R.raw.what);
                        mp[0].start();
                    }
                }
                if (hours >= 8) {
                    Toast toast = Toast.makeText(getApplicationContext(), "An all nighter!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
            }
            case 4 -> {
                beersPerHour = input;
                if (beersPerHour >= 1 && beersPerHour <= 2) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Hard to get a buzz at that rate.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (beersPerHour >= 3 && beersPerHour <= 4) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "A nice steady pace.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                    if (mp[0] != null) {
                        if (mp[0].isPlaying()) {
                            mp[0].stop();
                            mp[0].release();
                        }
                        mp[0] = MediaPlayer.create(MainActivity.this, R.raw.threefour);
                        mp[0].start();
                    }
                }
                if (beersPerHour == 5) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Going to tie one on!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (beersPerHour == 6) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "There will be hangovers.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
                if (beersPerHour >= 7) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Bring on the coma!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 50);
                    toast.show();
                }
            }
            case 5 -> averagePricePerCase = input;
        }

        if (currentStep <= 5) {
            askNextQuestion();
        } else {
            DecimalFormat df = new DecimalFormat("##,##0.00");
            double cases = Math.ceil(guests * hours * beersPerHour / 24);
            double cost = (cases * averagePricePerCase);
            double income = (guests * doorfee);
            double totalCost = (income - cost);
            String totalCost2 = String.format(Locale.ENGLISH, df.format(totalCost));
            String cases2 = String.format(Locale.ENGLISH, "%,.0f", cases);
            if (totalCost < 0) {
                resultTextView.setText(String.format("You need %s cases, and your cost will be $%s", cases2, totalCost2));
            }
            else if  (totalCost > 0) {
                resultTextView.setTextColor(parseColor("#7CFC00"));
                resultTextView.setText(String.format("You need %s cases, and your profit will be $%s", cases2, totalCost2));
            }
            else {
                resultTextView.setTextColor(parseColor("#7CFC00"));
                resultTextView.setText(String.format("You need %s  cases, and it looks like your breaking even. $%s", cases2, totalCost2));
            }
            inputEditText.setText("");
            inputEditText.setEnabled(false);
            submitButton.setEnabled(false);
            inputEditText.setVisibility(View.INVISIBLE);
            restartButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
        }
    }
}
