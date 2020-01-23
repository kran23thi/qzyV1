package com.example.qzy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

//public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainActivity.ItemClickListener
public class MainActivity extends AppCompatActivity {

    FloatingActionButton BtnAddQuiz;
    TextView QzDetailsView;
    //ArrayList<Quiz> quizes;
    RecyclerView Recycler_Quizes;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Main", "Begin in Main thread");
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        BtnAddQuiz = findViewById(R.id.AddQuiz);
        BtnAddQuiz.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Log.e("Main", "Add Button Clicked");
                                              Intent intent = new Intent(v.getContext(), AddQuizActivity.class);
                                              startActivity(intent);
                                              //getParent().startActivity(intent);
                                          }
                                      }
        );
        Recycler_Quizes = findViewById(R.id.Recycler_Quizes);
        QzDetailsView = findViewById(R.id.QzDetailsView);
        Recycler_Quizes.setLayoutManager(new LinearLayoutManager(this));
        Log.e("Main", "1 LayoutMnageger Set");

        Log.e("Main", "LayoutMnageger Set");
        //Recycler_Quizes.setAdapter();
        Recycler_Quizes.setAdapter(new Recycler_Quizes_Adapter(context));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_land_main);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        }
    }
}
