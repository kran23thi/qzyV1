package com.example.qzy;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;
//Exercise5
public class AsynchDbGetall extends AsyncTask {

    QzyDb qzyDb;
    Context context;
    ArrayList<Quiz> quizes;

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.e("AsynchDbGetall","Asyncch DB Get all started");
        context= (Context) objects[0];
        String qzName=(String) objects[1];
        Log.e("AsynchDbGetall","Sleeping");
        //sleep(10000); //Exercise5 - Tested sleep, app waits for completion.
        qzyDb=new QzyDb(context,qzName,null,1);
        quizes=qzyDb.getAllQuiz();
        return quizes;
    }
}
