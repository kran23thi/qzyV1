package com.example.qzy;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoadQuestion extends AppCompatActivity {
    String Title;
    Button BtnStartQuiz, BtnNextQ;
    Integer QCount=0;
    Integer qScore=0;
    TextView ScoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_question);
        Log.e("Load Question", "Setting up Fragments");

        Title=getIntent().getStringExtra("Title");
        Log.e("LoadQuestion","Title: "+ Title);

        ScoreText=findViewById(R.id.ScoreText);
        ScoreText.setText("0");

        BtnStartQuiz = findViewById(R.id.QuizStart);
        BtnStartQuiz.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Class fragmentClass;
                                              fragmentClass = ShowQuestion.class;
                                              loadQ(fragmentClass);
                                              QCount=QCount+1;
                                            }

                                      }
        );
        BtnNextQ = findViewById(R.id.NextQstart);
        BtnNextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(QCount>3)
                {
                Log.e("LoadQ","All Questions Completed");
                    Log.e("Main", "ENd Quiz");

                    Class fragmentClass;
                    fragmentClass = EndQuiz.class;
                    loadQ(fragmentClass);
                    /*
                    Fragment fragment = null;
                    Class fragmentClass;
                    fragmentClass = EndQuiz.class;
                    //for(int i=0;i<=3;i++)
                    {
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Bundle args = new Bundle();
                        args.putString("Title", Title);
                        args.putInt("seq", QCount);
                        fragment.setArguments(args);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                    }

                     */

                }
                else {
                    Log.e("LoadQ","Loading Next Q");
                    Class fragmentClass;
                    fragmentClass = ShowQuestion.class;
                    loadQ(fragmentClass);
                    QCount = QCount + 1;
                }
            }
        });
        {

        }
    }

    public void loadQ(Class fragmentClass)
    {
        Log.e("LoadQ", "Start Quiz Clicked");
        Fragment fragment = null;

            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Bundle args=new Bundle();
            args.putString("Title",Title);
            args.putInt("seq",QCount);
            fragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


    }

    }
