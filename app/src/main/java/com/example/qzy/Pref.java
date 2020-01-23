package com.example.qzy;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pref extends Fragment {

SharedPreferences sharedPreferences;
EditText editText;
EditText editEmail;
TextView displaytext;
    public Pref() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pref,container,false);
        Environment.getDataDirectory();
        sharedPreferences=view.getContext().getApplicationContext().getSharedPreferences(getString(R.string.preferece_file_key), Context.MODE_PRIVATE);
        Log.e("Pref","Pref:"+sharedPreferences.getAll().toString());
        editText = view.findViewById(R.id.NameEdit);
        editEmail=view.findViewById(R.id.EmailEditText);
        displaytext=view.findViewById(R.id.TxtCurPref);
        Button saveBtn=view.findViewById(R.id.SaveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Log.e("pref", "Preference saving");
                                              SharedPreferences.Editor editor=sharedPreferences.edit();
                                              editor.putString("name",editText.getText().toString());
                                              editor.putString("email",editEmail.getText().toString());
                                              editor.commit();
                                              displaytext.setText("Name:"+sharedPreferences.getString("name","Name")+"  Email:"+sharedPreferences.getString("email","Email"));
                                              //getParent().startActivity(intent);
                                          }
                                      });
/*

        if(sharedPreferences==null)
        {
            //SharedPreferences.Editor editor=sharedPreferences.edit();
            editor=sharedPreferences.edit();
            //EditText editText = view.findViewById(R.id.NameEdit);
            editText = view.findViewById(R.id.NameEdit);
            editor.putString("name",editText.getText().toString());
            editor.commit();
        }

*/
        return view;


    }

}
