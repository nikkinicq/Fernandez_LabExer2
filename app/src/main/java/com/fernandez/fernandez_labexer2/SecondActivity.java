package com.fernandez.fernandez_labexer2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {

    Button bt_LoadSharedPref;
    Button bt_LoadInternalStorage;
    Button bt_Clear;
    Button bt_Back;
    TextView tv_Display;
    FileInputStream fis;
    BufferedReader br;
    String user;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        bt_LoadSharedPref = (Button) findViewById(R.id.bt_loadShared);
        bt_LoadInternalStorage = (Button) findViewById(R.id.bt_loadShared);
        bt_LoadInternalStorage = (Button) findViewById(R.id.bt_loadInternal);
        bt_Clear = (Button) findViewById(R.id.bt_clear);
        bt_Back = (Button) findViewById(R.id.bt_back);
        tv_Display = (TextView) findViewById(R.id.tv_display);
    }

    public void callBack (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void callClear (View view) {
        tv_Display.setText("");
    }

    public void callLoadPreferences (View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        String user = preferences.getString("username","");
        String pwd = preferences.getString("password","");
        tv_Display.setText("The password of " + user + " is " + pwd);
    }

    public void callLoadInternalStorage (View view) throws IOException {
        String read = "";
        try{
            fis = openFileInput("output.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            if ((read = br.readLine()) != null)
                user = read;
            if ((read = br.readLine()) != null)
                pwd = read;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_Display.setText("The password of " + user + " is " + pwd);
    }
}