package com.zied.nasri.www_sms.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zied.nasri.www_sms.R;
import com.zied.nasri.www_sms.Tools.ContactTools;
import com.zied.nasri.www_sms.Tools.DateTools;
import com.zied.nasri.www_sms.database.entities.Message;
import com.zied.nasri.www_sms.listeners.IMessageClickListener;

import java.util.List;

import me.fahmisdk6.avatarview.AvatarView;


public class MessagesThreadRecyclerViewAdapter extends RecyclerView.Adapter<MessagesThreadRecyclerViewAdapter.ViewHolder> {

    private List<Message> mData;
    private LayoutInflater mInflater;
    private Context context;

    public MessagesThreadRecyclerViewAdapter(Context context, List<Message> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.message_thread_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = mData.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    Message getItem(int id) {
        return mData.get(id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView type;
        TextView date;
        TextView body;
        TextView time;

        ViewHolder(View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.message_thread_type);
            date = itemView.findViewById(R.id.message_thread_date);
            body = itemView.findViewById(R.id.message_thred_body);
            time = itemView.findViewById(R.id.message_thred_time);
        }

        public void bind(Message message){

            String dateLong = message.getDate();
            String dateStr = DateTools.fromLongStringLongFormat(dateLong);
            String timeStr = DateTools.timeFromLongStringLongFormat(dateLong);

            date.setText(dateStr);
            body.setText(message.getBody());
            time.setText(timeStr);

            type.setVisibility(View.GONE);
            if(getAdapterPosition() == 0)type.setVisibility(View.VISIBLE);

            //type.setText(message.getType());
        }

    }

}
