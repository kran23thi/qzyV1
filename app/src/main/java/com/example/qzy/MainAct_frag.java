package com.example.qzy;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainAct_frag extends Fragment {
    FloatingActionButton BtnAddQuiz;
    TextView QzDetailsView;
    //ArrayList<Quiz> quizes;
    RecyclerView Recycler_Quizes;
    Context context;

    public MainAct_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_main_act_frag, container, false);
        // Inflate the layout for this fragment
        Log.e("Main", "Begin in Main thread");

        BtnAddQuiz = view.findViewById(R.id.AddQuiz);
         BtnAddQuiz.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Log.e("Main", "Add Button Clicked");
                                              //Intent intent = new Intent(v.getContext(), AddQuizActivity.class);
                                              Intent intent=new Intent(v.getContext(),AddQuizQs.class);
                                              startActivity(intent);
                                              //getParent().startActivity(intent);
                                          }
                                      }
        );
        context = view.getContext().getApplicationContext();
        Recycler_Quizes = view.findViewById(R.id.Recycler_Quizes);
        QzDetailsView = view.findViewById(R.id.QzDetailsView);
        Recycler_Quizes.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Log.e("Main", "1 LayoutMnageger Set");

        Log.e("Main", "LayoutMnageger Set");
        //Recycler_Quizes.setAdapter();
        Recycler_Quizes.setAdapter(new Recycler_Quizes_Adapter(view.getContext()));
        return view;
        //return inflater.inflate(R.layout.fragment_main_act_frag, container, false);
    }


}
