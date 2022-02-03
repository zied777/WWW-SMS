package com.zied.nasri.www_sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NewMessageActivity extends AppCompatActivity {

    private class XView {

        private void init(){

            getSupportActionBar().setTitle("New message");
        }
    }

    private XView xview = new XView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        xview.init();
    }

    public static Intent getStarterIntent(Context context){

        Intent intent = new Intent(context, NewMessageActivity.class);
        return intent;
    }
}