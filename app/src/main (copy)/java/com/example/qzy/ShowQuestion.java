package com.example.qzy;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowQuestion extends Fragment {
    TextView QuestionDisplay;
    RadioButton OptionA, OptionB,OptionC,OptionD;
    RadioGroup OptionsRadio;
    Button SubmitQA;
    String Title;
    int seq;
    QzyDb qzyDb;
    Context context;
    Quiz quiz;
    AsynchDbGetQuiz asynchDbGetQuiz;
    AsynchDBGetQs asynchDBGetQs;
    Question question;
    Integer qscore,cal;
    TextView ScoreText;

    public ShowQuestion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout forthis fragment
        final View view=inflater.inflate(R.layout.fragment_show_question, container, false);
        Log.e("Show Question", "Show Question loading");
        Bundle args=this.getArguments();

        if(args!=null)
        {
            Title=args.getString("Title");
            Log.e("ShowQuestion","Title: "+ Title);
            seq=args.getInt("seq");
        }
        context=view.getContext().getApplicationContext();

        question=getQuestion(Title,seq);
        Log.e("ShowQuestion","question: "+ question.Category);
        QuestionDisplay=view.findViewById(R.id.QuestionDisplay);
        OptionA=view.findViewById(R.id.OptionA);
        OptionB=view.findViewById(R.id.OptionB);
        OptionC=view.findViewById(R.id.OptionC);
        OptionD=view.findViewById(R.id.OptionD);
        QuestionDisplay.setText(question.Question);
        OptionA.setText(question.OptionA);                                              //getParent().startActivity(intent);

        OptionB.setText(question.OptionB);
        OptionC.setText(question.OptionC);
        OptionD.setText(question.OptionD);
        //uestionDisplay.setText(view);
        OptionsRadio=view.findViewById(R.id.OptionsR);
        SubmitQA=view.findViewById(R.id.SubmitQA);

        OptionsRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                    @Override
                                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                        Log.e("Show Question", "SubmitQA Radio Clicked");
/*
                                                        Log.e("ShowQ ","Selected Radio switch");
                                                        switch (checkedId)
                                                        {
                                                            case R.id.OptionA:
                                                                if(OptionA.getText()==question.Answer)
                                                                    RunCorrect();
                                                                else
                                                                    RunWrong();
                                                                    break;
                                                            case R.id.OptionB:
                                                                if(OptionA.getText()==question.Answer)
                                                                    RunCorrect();
                                                                else
                                                                    RunWrong();
                                                                break;
                                                            case R.id.OptionC:
                                                                if(OptionA.getText()==question.Answer)
                                                                    RunCorrect();
                                                                else
                                                                    RunWrong();
                                                                break;
                                                            case R.id.OptionD:
                                                                if(OptionA.getText()==question.Answer)
                                                                    RunCorrect();
                                                                else
                                                                    RunWrong();
                                                                break;
                                                        }*/
                                                        //Intent intent = new Intent(v.getContext(), AddQuizActivity.class);
                                                        RadioButton RadioSel=(RadioButton)view.findViewById(checkedId);
                                                        Log.e("ShowQ ","Selected Radio"+RadioSel.getText());
                                                        Log.e("ShowQ","Answer: "+question.Answer);
                                                        if(question.Answer.equals(RadioSel.getText()))
                                                        {
                                                            RunCorrect();
                                                        }
                                                        else
                                                        {
                                                            RunWrong();
                                                        }
                                                    }


                                                });


        SubmitQA.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Log.e("Show Question", "SubmitQA Clicked Clicked");
                                              //Intent intent = new Intent(v.getContext(), AddQuizActivity.class);
                                              RadioButton RadioSel=(RadioButton)view.findViewById(OptionsRadio.getCheckedRadioButtonId());
                                              if(question.Answer==RadioSel.getText().toString())
                                                  {
                                                      Toast toast=Toast.makeText(v.getContext().getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT);
                                                      toast.show();
                                                      qzyDb=new QzyDb(context,"Qzy",null,1);
                                                      qzyDb.addScore(quiz.quizId,25);

                                                  }
                                              else
                                              {
                                                  Toast toast=Toast.makeText(v.getContext().getApplicationContext(),"Wrong answer",Toast.LENGTH_SHORT);
                                                  toast.show();
                                              }

                                          }
                                      }
        );

        return view;
    }

    public Question getQuestion(String Qid,int seq)
    {


        asynchDbGetQuiz=new AsynchDbGetQuiz();
        Object obj[]=new Object[3];
        obj[0]=context;
        obj[1]="Qzy";
        obj[2]=Title;
        quiz=(Quiz)asynchDbGetQuiz.doInBackground(obj);

        Log.e("ShowQs","Quiz category"+quiz.Category);
        Log.e("ShowQs","Quiz Title"+quiz.Title);
        Log.e("ShowQs","Quiz Questions"+quiz.Questions);
        String qs=quiz.Questions;
        String qids[]=qs.split(",");
        String qid=qids[seq];

        asynchDBGetQs=new AsynchDBGetQs();
        Object obj1[]=new Object[3];
        obj[0]=context;
        obj[1]="Qzy";
        obj[2]=qid;
        question=(Question)asynchDBGetQs.doInBackground(obj);
        return question;
    }

    public void RunCorrect()
    {
        Toast toast=Toast.makeText(getContext().getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT);
        toast.show();

        ScoreText=getActivity().findViewById(R.id.ScoreText);
        String sc=ScoreText.getText().toString();
        Log.e("ShowQ","Score Text shows:"+sc);

        try{

        }
        catch (NumberFormatException nfe)
        {
            Log.e("error","Number format converstion error");
        }
        cal=Integer.parseInt(sc.replaceAll("[\\D]",""));
        cal=cal+25;
        ScoreText.setText(cal.toString());
        /*.
        qzyDb=new QzyDb(context,"Qzy",null,1);

        qzyDb.addScore(quiz.quizId,25);

*/
    }

    public void RunWrong()
    {
        Toast toast=Toast.makeText(getContext().getApplicationContext(),"Wrong answer",Toast.LENGTH_SHORT);
        toast.show();
    }



}
