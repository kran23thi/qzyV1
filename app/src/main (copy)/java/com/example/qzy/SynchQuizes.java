package com.example.qzy;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class SynchQuizes extends Service {
    public SynchQuizes() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Synch Service","Synch service onStart");
        synchQuizes();
        synchQs();

  return super.onStartCommand(intent, flags, startId);
    }

    public void synchQuizes()
    {
        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(this.loadJSONFromAsset("QuizesList.json"));
            // fetch JSONArray named Quizes
            JSONArray QuizArray = obj.getJSONArray("Quizes");
            // implement for loop for getting users list data
            for (int i = 0; i < QuizArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject QuizDetail = QuizArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                Log.e("AddQuiz","Add quiz requested");

                ContentValues values=new ContentValues();
                values.put("quizId",QuizDetail.getString("QuizId"));
                values.put("Title",QuizDetail.getString("Title"));
                values.put("Category",QuizDetail.getString("Category"));
                values.put("postedDate",QuizDetail.getString("postedDate"));
                values.put("Questions",QuizDetail.getString("Questions"));
                Uri uri=getContentResolver().insert(QzyDataProvider.CONTENT_URI, values);
                Log.e("SynchQuiz","Add quiz completed for: "+ values.get("Title"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void synchQs() {
        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset("QsList.json"));
            // fetch JSONArray named Quizes
            JSONArray QuizArray = obj.getJSONArray("Qs");
            // implement for loop for getting users list data
            for (int i = 0; i < QuizArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject QuizDetail = QuizArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                Log.e("AddQService", "Add question requested");

                ContentValues values = new ContentValues();
                values.put("Qid", QuizDetail.getString("Qid"));
                values.put("Question", QuizDetail.getString("Question"));
                values.put("Category", QuizDetail.getString("Category"));
                values.put("OptionA", QuizDetail.getString("OptionA"));
                values.put("OptionB", QuizDetail.getString("OptionB"));
                values.put("OptionC", QuizDetail.getString("OptionC"));
                values.put("OptionD", QuizDetail.getString("OptionD"));
                values.put("Answer", QuizDetail.getString("Answer"));
                Uri uri = getContentResolver().insert(QzyQProvider.CONTENT_URI, values);
                Log.e("SynchQuiz", "Add question completed for: " + values.get("Title"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

        public String loadJSONFromAsset(String flname) {
        String json = null;
        try {
            InputStream is = getAssets().open(flname);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
