package com.example.qzy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class Recycler_Quizes_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    ArrayList<Quiz> quizes;
    QzyDb qzyDb;
    Context context;
    AsynchDbGetall asynchDbGetall;

    public Recycler_Quizes_Adapter(Context c)
    {
        context=c;
        Log.e("Adapter","new quiz adapter context "+c.toString());
        asynchDbGetall=new AsynchDbGetall();
        Object obj[]=new Object[2];
        obj[0]=context;
        obj[1]="Qzy";
        quizes=(ArrayList<Quiz>)asynchDbGetall.doInBackground(obj); //Exercise5 Assynch Task used for DB Ops
        //qzyDb=new QzyDb(c,"Qzy",null,1);
        //quizes=qzyDb.getAllQuiz();
    }

    class QuizItem extends RecyclerView.ViewHolder {
        TextView QzItemText;
        private ItemClickListener clickListener;

        public QuizItem(@NonNull View itemView) {
            super(itemView);
            QzItemText=itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener(){

                                            @Override
                                            public void onClick(View v) {
                                                Log.e("Adapter","Item Clicked");
                                                Log.e("Adapeter","Context is"+v.getParent().getParent().toString());
                                                int orientation = v.getResources().getConfiguration().orientation;
                                                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                                    Intent intent=new Intent(v.getContext(),LandMainActivity.class);
                                                    Log.e("Adapter Item",QzItemText.getText().toString());
                                                    intent.putExtra("quizId",QzItemText.getText().toString());
                                                    intent.putExtra("orientation","Land");
                                                    v.getContext().startActivity(intent);

                                                } else {
                                                    Intent intent=new Intent(v.getContext(),QuizDeatails.class);
                                                    Log.e("Adapter Item",QzItemText.getText().toString());
                                                    intent.putExtra("quizId",QzItemText.getText().toString());
                                                    v.getContext().startActivity(intent);
                                                }


                                            }
                                        }
            );
        }
        private Quiz getData()
        {
            if(getAdapterPosition()>=0&&getAdapterPosition()<quizes.size())
            {
                return quizes.get(getAdapterPosition());
            }
            else
            {
                return null;
            }
        }

        void configureItem()
        {
            Quiz quizObj=getData();
            if(quizObj!=null)
            {
                QzItemText.setText(quizObj.Title);
            }
        }

        /*@Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
            Log.e("Adapter","Item Clicked");
            Intent intent=new Intent(v.getContext(),QuizDeatails.class);
            v.getContext().startActivity(intent);
        }*/

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1,viewGroup,false);
        return new QuizItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((QuizItem)viewHolder).configureItem();
    }

    @Override
    public int getItemCount() {
        return quizes.size();
    }
}
