package com.zied.nasri.www_sms.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zied.nasri.www_sms.R;
import com.zied.nasri.www_sms.Tools.ContactTools;
import com.zied.nasri.www_sms.database.entities.Message;
import com.zied.nasri.www_sms.listeners.IMessageClickListener;

import java.util.List;

import me.fahmisdk6.avatarview.AvatarView;


public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder> {

    private List<Message> mData;
    private LayoutInflater mInflater;
    private Context context;
    private IMessageClickListener iMessageClickListener;

    public MessagesRecyclerViewAdapter(Context context, List<Message> data, IMessageClickListener iMessageClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.iMessageClickListener = iMessageClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.message_layout, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AvatarView rowImageView;
        TextView rowNameTextView;
        TextView rowMessageBodyTextView;
        TextView rowDateTextView;

        ViewHolder(View itemView) {
            super(itemView);

            rowImageView = itemView.findViewById(R.id.message_layout_image_1);
            rowNameTextView = itemView.findViewById(R.id.message_layout_text_1);
            rowMessageBodyTextView = itemView.findViewById(R.id.message_layout_text_2);
            rowDateTextView = itemView.findViewById(R.id.message_layout_text_3);

            itemView.setOnClickListener(this);
        }

        public void bind(Message message){

            new ContactTools(context, rowImageView, message.getAddress()).addThumbnail();
            rowNameTextView.setText(message.getAddress());
            rowMessageBodyTextView.setText(message.getBody());

            String relativeDate = DateUtils.getRelativeDateTimeString(
                    context,
                    Long.parseLong(message.getDate()),
                    DateUtils.DAY_IN_MILLIS,
                    DateUtils.WEEK_IN_MILLIS,
                    0
            ).toString();
            rowDateTextView.setText(relativeDate);

        }

        @Override
        public void onClick(View view) {
            iMessageClickListener.onClick(getItem(getAdapterPosition()));
        }
    }

}
