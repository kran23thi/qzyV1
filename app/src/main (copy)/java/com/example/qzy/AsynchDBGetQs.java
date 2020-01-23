package com.example.qzy;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class AsynchDBGetQs extends AsyncTask {
    QzyDb qzyDb;
    Context context;
    Cursor cursor;
    @Override
    protected Object doInBackground(Object[] objects) {
        Log.e("Asynch Q get","Asyncch DB Get Question started");

        context= (Context) objects[0];
        String db=(String) objects[1];
        String qid=(String) objects[2];
        //Log.e("AsynchDbGetall","Sleeping");
        //sleep(10000); //Exercise5 - Tested sleep, app waits for completion.

        //Content resolutio
        String URL=QzyQProvider.CONTENT_URI+"/getQ/Qid/"+qid;
        Log.e("GetDeatails","Content Resolver for: "+URL);
        Uri qzuri=Uri.parse(URL);
        Log.e("GetDeatails","qzuri: "+qzuri);
        cursor=this.context.getContentResolver().query(qzuri, null, null, null, "name");

        Question question=new Question();
        cursor.moveToFirst();

        if(cursor.isFirst())
        {
            while(!cursor.isAfterLast())
            {

                question.Qid=cursor.getString(cursor.getColumnIndex("Qid"));
                question.Question=cursor.getString(cursor.getColumnIndex("Question"));
                question.Category=cursor.getString(cursor.getColumnIndex("Category"));
                question.OptionA=cursor.getString(cursor.getColumnIndex("OptionA"));
                question.OptionB=cursor.getString(cursor.getColumnIndex("OptionB"));
                question.OptionC=cursor.getString(cursor.getColumnIndex("OptionC"));
                question.OptionD=cursor.getString(cursor.getColumnIndex("OptionD"));
                question.Answer=cursor.getString(cursor.getColumnIndex("Answer"));

                Log.e("GetQAsynch","QId: "+question.Qid);
            cursor.moveToNext();
            }

        }
        cursor.close();

        //Content resolution

        //qzyDb=new QzyDb(context,qzName,null,1);
        //Quiz quiz=qzyDb.getQuiz(qzid);

        return question;
    }

}
