package com.example.kitchen_glovesapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import static androidx.constraintlayout.solver.widgets.ConstraintWidget.INVISIBLE;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private TextView badPassword;
    public Switch switch1;
    public Switch mainswitch;

    public Switch switch2;
    public Switch switch3;
    public EditText pinfield;

    boolean onFlag1;
    boolean onFlag2;
    boolean onFlag3;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragment = new glowica();
                    loadFragment(fragment);
                    System.out.println("glowica");


                    return true;
                case R.id.navigation_dashboard:

                    fragment = new tryby();
                    loadFragment(fragment);
                    System.out.println("glowica");



                    return true;
                case R.id.navigation_notifications:
                    fragment = new obroty();
                    loadFragment(fragment);




                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new glowica());


    }


    private void preLoadElements()
    {
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);

        badPassword = findViewById(R.id.badPassword);
        pinfield = (EditText) findViewById(R.id.pinfield);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void passwordMethod1(View view) {

                preLoadElements();
                    if(!onFlag1){
                      switch1.setChecked(!switch1.isChecked());
                      onFlag1=true;
                    }
                pinfield.setVisibility(View.VISIBLE);
    }

    public void passwordMethod2(View view) {
                preLoadElements();
                    if(!onFlag2){
                      switch2.setChecked(!switch2.isChecked());
                      onFlag2=true;
                    }
                pinfield.setVisibility(View.VISIBLE);
    }

    public void passwordMethod3(View view) {
                preLoadElements();
                pinfield.setVisibility(View.VISIBLE);
                     if(!onFlag3){
                        switch3.setChecked(!switch3.isChecked());
                        onFlag3=true;
                     }
    }


    public void checkPassword(View view) {

        pinfield.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String password="0";
                    if(keyCode == KeyEvent.KEYCODE_ENTER ){
                        password=pinfield.getText().toString();

                             if(password.equals("1")){
                                switch1.setChecked(true);
                                badPassword.setVisibility(View.INVISIBLE);
                             }

                            else if(password.equals("2")){
                                switch2.setChecked(true);
                                badPassword.setVisibility(View.INVISIBLE);
                            }

                            else if(password.equals("3")){
                                switch3.setChecked(true);
                                badPassword.setVisibility(View.INVISIBLE);
                            }

                            else{
                                badPassword.setVisibility(View.VISIBLE);
                            }

                      return true;
                }
              return false;
            }
        });







    }

    public void getSpeech(View view) {

        mainswitch = findViewById(R.id.mainswitch);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Uruchom głosem - powiedz ON lub OFF...");

        startActivityForResult(intent, 10);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode==RESULT_OK && null!= data){
            ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (voiceInText.get(0)=="hi"){

                    mainswitch.setChecked(true);
                }


            System.out.println(voiceInText.get(0));



        }
    }




}


