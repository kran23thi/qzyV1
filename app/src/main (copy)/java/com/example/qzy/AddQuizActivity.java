package com.example.qzy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuizActivity extends AppCompatActivity implements View.OnClickListener {
    Button AdqzBtn;
    EditText EditTextQuizId,EditTextQuizTitle,EditTextQuizCatagory,EditTextQuiztags,EditTextQuizAddedDt;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Add Quiz","Add quiz loaded");
        c=getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        AdqzBtn=findViewById(R.id.BtnAddQuiz);
        AdqzBtn.setOnClickListener(this);
        EditTextQuizId=findViewById(R.id.EditTextQuizId);
        EditTextQuizTitle=findViewById(R.id.EditTextQuizTitle);
        EditTextQuizCatagory=findViewById(R.id.EditTextQuizCatagory);
        EditTextQuiztags=findViewById(R.id.EditTextQuiztags);
        EditTextQuizAddedDt=findViewById(R.id.EditTextQuizAddedDt);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.BtnAddQuiz)
        {
            Log.e("AddQuiz","Add quiz requested");

            ContentValues values=new ContentValues();
            values.put("Title",EditTextQuizTitle.getText().toString());
            values.put("Category",EditTextQuizCatagory.getText().toString());
            values.put("postedDate",EditTextQuizAddedDt.getText().toString());

            Uri uri=getContentResolver().insert(QzyDataProvider.CONTENT_URI, values);
            Log.e("AddQuiz","Add quiz completed");
            Toast.makeText(getBaseContext(),
                    uri.toString(), Toast.LENGTH_LONG).show();
            //QzyDb qzyDb=new QzyDb(c,"Qzy",null,1);
            //qzyDb.insertIntoQuizDb(EditTextQuizId.getText().toString(),EditTextQuizTitle.getText().toString(),EditTextQuizCatagory.getText().toString(),EditTextQuizAddedDt.getText().toString());

        }
    }
}
