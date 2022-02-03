package com.zied.nasri.www_sms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zied.nasri.www_sms.R;
import com.zied.nasri.www_sms.Tools.ContactTools;
import com.zied.nasri.www_sms.database.entities.Contact;

import java.util.ArrayList;
import java.util.List;

import me.fahmisdk6.avatarview.AvatarView;

public class ContactsArrayAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private List<Contact> data;
    private ArrayList<Contact> suggestions = new ArrayList<>();
    private List<Contact> tempItems;

    public ContactsArrayAdapter(@NonNull Context context, List<Contact> data) {
        super(context, R.layout.contact_info_layout,data);
        this.context = context;
        this.data = data;

        tempItems = new ArrayList<Contact>(data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.contact_info_layout, null);
        }
        ContactViewHolder contactViewHolder = (ContactViewHolder) convertView.getTag();
        if(contactViewHolder == null){
            contactViewHolder = new ContactViewHolder(convertView);
            convertView.setTag(contactViewHolder);
        }
        contactViewHolder.bind(data.get(position));
        return convertView;
    }

    private class ContactViewHolder{

        private AvatarView avatarView;
        private TextView nameTextView;
        private TextView phoneTextView;

        public ContactViewHolder(View itemView){

            avatarView = itemView.findViewById(R.id.contact_info_layout_image_1);
            nameTextView = itemView.findViewById(R.id.contact_info_layout_text_1);
            phoneTextView = itemView.findViewById(R.id.contact_info_layout_text_2);
        }

        public void bind(Contact contact){

            new ContactTools(context, avatarView, contact.getName()).addThumbnail();
            nameTextView.setText(contact.getName());
            phoneTextView.setText(contact.getPhoneNumber());
        }
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        public String convertResultToString(Object resultValue) {
            String str = ((Contact) (resultValue)).getName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Contact product : tempItems) {
                    if (product.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(product);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            @SuppressWarnings("unchecked")
            ArrayList<Contact> filteredList = (ArrayList<Contact>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Contact c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };
}
