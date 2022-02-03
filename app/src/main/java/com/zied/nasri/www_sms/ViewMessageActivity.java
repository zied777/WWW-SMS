package com.zied.nasri.www_sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.zied.nasri.www_sms.Tools.ViewTools;
import com.zied.nasri.www_sms.adapters.LockFragmentsPagerAdapter;
import com.zied.nasri.www_sms.adapters.MessagesRecyclerViewAdapter;
import com.zied.nasri.www_sms.adapters.MessagesThreadRecyclerViewAdapter;
import com.zied.nasri.www_sms.core.components.data.IDataRequest;
import com.zied.nasri.www_sms.core.components.storage.AppStorage;
import com.zied.nasri.www_sms.database.entities.Message;
import com.zied.nasri.www_sms.fragments.WhenFragment;
import com.zied.nasri.www_sms.fragments.WhereFragment;
import com.zied.nasri.www_sms.fragments.WhoFragment;
import com.zied.nasri.www_sms.listeners.IWWWFragmentListener;

import java.util.List;

public class ViewMessageActivity extends AppCompatActivity {

    public static final String THREAD_ID = "thread_id";
    public static final String ADDRESS = "address";

    public class Core {

        private void init(){
            AppStorage.getInstance(ViewMessageActivity.this).getAllByThreadId(model.threadId, new IDataRequest() {
                @Override
                public void onDataReady(Object data) {
                    model.data = (List<Message>)(List<?>)data;
                    showThreadMessages();
                }
            });
        }

        public void showThreadMessages() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.bind(model);
                }
            });
        }

    }

    public class Model {

        String threadId;
        String address;
        List<Message> data;

        private void init(Intent intent){
            threadId = intent.getExtras().getString(THREAD_ID);
            address = intent.getExtras().getString(ADDRESS);
        }
    }

    public class View {

        RecyclerView recyclerView;
        MessagesThreadRecyclerViewAdapter recyclerViewAdapter;


        private void init(){

            ViewMessageActivity.this.getSupportActionBar().setTitle(model.address);

            recyclerView = findViewById(R.id.activity_view_message_layout_rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(ViewMessageActivity.this));

        }

        private void bind(Model model){

            recyclerViewAdapter = new MessagesThreadRecyclerViewAdapter(ViewMessageActivity.this, model.data);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.scrollToPosition(model.data.size() - 1);

        }

    }

    public Core core = new Core();
    public Model model = new Model();
    public View view = new View();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        model.init(getIntent());
        core.init();
        view.init();
    }


    public static Intent getStarterIntent(Context context, String threadId, String address){

        Intent intent = new Intent(context, ViewMessageActivity.class);
        intent.putExtra(THREAD_ID,threadId);
        intent.putExtra(ADDRESS,address);

        return intent;
    }
}