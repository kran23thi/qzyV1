package com.example.qzy;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuizQs extends AppCompatActivity implements View.OnClickListener {
    Button AdQBtn;
    EditText EditTextQid,EditTextQ, EditTextQCatagory, EditTextQOptA,EditTextQOptB,EditTextQOptC,EditTextQOptD,QAnswer;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_qs);
        c=getApplicationContext();
        AdQBtn=findViewById(R.id.BtnAddQ);
        EditTextQ=findViewById(R.id.EditTextQ);
        EditTextQid=findViewById(R.id.EditTextQId);
        AdQBtn.setOnClickListener(this);
        EditTextQCatagory=findViewById(R.id.EditTextQCatagory);
        EditTextQOptA=findViewById(R.id.EditTextQOptA);
        EditTextQOptB=findViewById(R.id.EditTextQOptB);
        EditTextQOptC=findViewById(R.id.EditTextQOptC);
        EditTextQOptD=findViewById(R.id.EditTextQOptD);
        QAnswer=findViewById(R.id.QAnswer);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.BtnAddQ)
        {
            Log.e("AddQzQ","Add question requested");
            ContentValues values=new ContentValues();
            values.put("Qid",EditTextQid.getText().toString());
            values.put("Question",EditTextQ.getText().toString());
            values.put("Category",EditTextQCatagory.getText().toString());
            values.put("OptionA",EditTextQOptA.getText().toString());
            values.put("OptionB",EditTextQOptB.getText().toString());
            values.put("OptionC",EditTextQOptC.getText().toString());
            values.put("OptionD",EditTextQOptD.getText().toString());
            values.put("Answer",QAnswer.getText().toString());

            Uri uri=getContentResolver().insert(QzyQProvider.CONTENT_URI, values);
            Log.e("AddQzQ","Add Q completed");
            Toast.makeText(getBaseContext(),
                    uri.toString(), Toast.LENGTH_LONG).show();
            //QzyDb qzyDb=new QzyDb(c,"Qzy",null,1);
            //qzyDb.insertIntoQuizDb(EditTextQuizId.getText().toString(),EditTextQuizTitle.getText().toString(),EditTextQuizCatagory.getText().toString(),EditTextQuizAddedDt.getText().toString());

        }
    }
}
