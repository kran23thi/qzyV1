package com.example.qzy;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class AsynchDbGetQuiz extends AsyncTask  implements LoaderManager.LoaderCallbacks {
    QzyDb qzyDb;
    Context context;
    Cursor cursor;
    CursorLoader cursorLoader;
    Quiz quiz;
    String quizId;
    Uri qzuri;

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.e("AsynchDbGetall", "Asyncch DB Get Quiz started");

        context = (Context) objects[0];
        String qzName = (String) objects[1];
        String qzid = (String) objects[2];
        //Log.e("AsynchDbGetall", "Sleeping");
        //sleep(10000); //Exercise5 - Tested sleep, app waits for completion.

        //Content resolution
        String URL = QzyDataProvider.CONTENT_URI + "/quizid/" + qzid;
        Log.e("GetDeatails", "Content Resolver for: " + URL);
        qzuri = Uri.parse(URL);
        quiz = new Quiz();
        //cursorLoader = new CursorLoader(this.context, qzuri, null, null, null, null);
        //cursorLoader.loadInBackground();

          cursor=this.context.getContentResolver().query(qzuri, null, null, null, "name");
        Log.e("GetDeatails","Content Resolver for: "+URL);

        cursor.moveToFirst();
        Quiz quiz=new Quiz();
        if(cursor.isFirst())
        {
                quiz.quizId=cursor.getString(cursor.getColumnIndex("quizId"));
                Log.e("GetQuiz","Quiz Id"+quiz.quizId);
                quiz.Title=cursor.getString(cursor.getColumnIndex("Title"));
                quiz.Category=cursor.getString(cursor.getColumnIndex("Category"));
                //  quiz.tags=cursor.getString(cursor.getColumnIndex("tags"));
                quiz.postedDate=cursor.getString(cursor.getColumnIndex("postedDate"));
                quiz.Questions=cursor.getString(cursor.getColumnIndex("Questions"));

        }


        //Content resolution

        //qzyDb=new QzyDb(context,qzName,null,1);
        //Quiz quiz=qzyDb.getQuiz(qzid);

        return quiz;
    }


    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {

        Loader<Cursor> loader = null;
                loader = new CursorLoader(this.context,
                        qzuri,
                        new String[] {"QUiz", "last_name", "id"},
                        null,
                        null,
                        "id ASC");
        return loader;
    }



    @Override
    public void onLoadFinished(@NonNull Loader loader, Object o) {
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            quizId = cursor.getString(cursor.getColumnIndex("quizId"));
           /*String storeName = cursor.getString(cursor.getColumnIndex(
               ProviderMetadata.AccountMetaData.REC_ACCOUNT_STORE_NAME));*/
            if (quizId != null) {
                Toast.makeText(this.context, cursor.getCount() + "", Toast.LENGTH_LONG).show();

                quiz.quizId = cursor.getString(cursor.getColumnIndex("quizId"));
                Log.e("GetQuiz", "Quiz Id" + quiz.quizId);
                quiz.Title = cursor.getString(cursor.getColumnIndex("Title"));
                quiz.Category = cursor.getString(cursor.getColumnIndex("Category"));
                //  quiz.tags=cursor.getString(cursor.getColumnIndex("tags"));
                quiz.postedDate = cursor.getString(cursor.getColumnIndex("postedDate"));
                quiz.Questions = cursor.getString(cursor.getColumnIndex("Questions"));
            }

            cursor.close();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}