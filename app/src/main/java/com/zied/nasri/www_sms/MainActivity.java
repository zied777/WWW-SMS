package com.zied.nasri.www_sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zied.nasri.www_sms.Tools.DialogTools;
import com.zied.nasri.www_sms.adapters.MessagesRecyclerViewAdapter;
import com.zied.nasri.www_sms.core.components.data.IDataRequest;
import com.zied.nasri.www_sms.core.components.permissions.IPermissionChecker;
import com.zied.nasri.www_sms.core.components.screens.IHomeScreen;
import com.zied.nasri.www_sms.core.components.storage.AppStorage;
import com.zied.nasri.www_sms.database.entities.Message;
import com.zied.nasri.www_sms.listeners.IMessageClickListener;

import java.util.List;

public class MainActivity extends BaseActivity implements IHomeScreen {

    private class Core implements IPermissionChecker, DialogInterface.OnClickListener, IMessageClickListener, android.view.View.OnClickListener {

        private void init(){

            AppStorage.getInstance(MainActivity.this).getAll(new IDataRequest() {
                @Override
                public void onDataReady(Object data) {
                    model.data = (List<Message>)(List<?>)data;
                    showListOfMessages(model.data);
                }
            });
        }

        public void showListOfMessages(List<Message> data) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.bind(model);
                }
            });
        }


        @Override
        public void onPermissionGranted() {
            init();
        }

        @Override
        public void onPermissionDenied() {
            DialogTools.showMessageDialog(MainActivity.this, "Finish Application !", this);
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            MainActivity.this.finish();
        }

        @Override
        public void onClick(Message message) {
            MainActivity.this.startActivity(ViewMessageActivity.getStarterIntent(MainActivity.this, message.getThread_id(), message.getAddress()));
        }

        @Override
        public void onClick(android.view.View view) {
            MainActivity.this.startActivity(NewMessageActivity.getStarterIntent(MainActivity.this));
        }
    }
    private class Model {

        List<Message> data;
    }

    private class View {

        RecyclerView recyclerView;
        MessagesRecyclerViewAdapter recyclerViewAdapter;
        FloatingActionButton floatingActionButton;

        private void init(){

            recyclerView = findViewById(R.id.activity_main_layout_rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            floatingActionButton = findViewById(R.id.floating_add_action_button);
        }

        private void bind(Model model){

            recyclerViewAdapter = new MessagesRecyclerViewAdapter(MainActivity.this, model.data, core);
            recyclerView.setAdapter(recyclerViewAdapter);
            floatingActionButton.setOnClickListener(core);
        }

    }

    private View view = new View();
    private Model model = new Model();
    private Core core = new Core();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view.init();
        checkReadSmsPermission(core);
    }


}