package com.example.qzy;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class EndQuiz extends Fragment {
    QzyDb qzyDb;
String quizid;
Context context;
Integer score;
TextView ScoreDisplay,ScoreText;
Date dt;
Integer cal;
Calendar daycal;
    public EndQuiz() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_end_quiz, container, false);
        Log.e("End Quiz", "End Quiz loading");
        Bundle args=this.getArguments();
        if(args!=null)
        {
            quizid=args.getString("Title");
            Log.e("EndQ","Title: "+ quizid);
            //seq=args.getInt("seq");
        }

        ScoreText=getActivity().findViewById(R.id.ScoreText);


        String sc=ScoreText.getText().toString();
        Log.e("ShowQ","Score Text shows:"+sc);

        try{
            cal=Integer.parseInt(sc.replaceAll("[\\D]",""));
            ScoreText.setText(cal.toString());
        }
        catch (NumberFormatException nfe)
        {
            Log.e("error","Number format converstion error");
        }

        context=view.getContext().getApplicationContext();
        qzyDb=new QzyDb(context,"Qzy",null,1);
        daycal=Calendar.getInstance();
        dt=daycal.getTime();
        String strDateFormat="dd:mm:yy";
        DateFormat dateFormat=new SimpleDateFormat(strDateFormat);
        String FormattedDate=dateFormat.format(dt);

        qzyDb.insertIntoScore(quizid,cal,FormattedDate);

        score=qzyDb.getQuizScore(quizid);
        ScoreDisplay=view.findViewById(R.id.ScoreDispay);
        Log.e("EndQ","Score: "+ score);
        ScoreDisplay.setText("Your Score:"+score.toString());
        return view;
    }

}
