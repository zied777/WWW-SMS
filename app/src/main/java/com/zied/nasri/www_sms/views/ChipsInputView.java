package com.zied.nasri.www_sms.views;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.zied.nasri.www_sms.R;
import com.zied.nasri.www_sms.Tools.ContactListTools;
import com.zied.nasri.www_sms.Tools.ContactTools;
import com.zied.nasri.www_sms.adapters.ContactsArrayAdapter;
import com.zied.nasri.www_sms.database.entities.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ChipsInputView extends FrameLayout {

    private Context context;
    private AutoCompleteTextView inputText;
    private FlexboxLayout chipGroup;
    private ContactsArrayAdapter contactsArrayAdapter;
    private ChipGroup chipsContactsGroup;


    public ChipsInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chips_input_view_layout,this,true);

        chipGroup = findViewById(R.id.recipient_group_FL);
        inputText = findViewById(R.id.recipient_input_ET);
        chipsContactsGroup = findViewById(R.id.chips_contacts);

        List<Contact> contacts = ContactListTools.getContactList(context);
        this.addChipsContact(contacts);
        contactsArrayAdapter = new ContactsArrayAdapter(context, contacts);
        contactsArrayAdapter.setNotifyOnChange(true);
        inputText.setAdapter(contactsArrayAdapter);

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() - editable.toString().trim().length() == 1) {
                    Contact contact = ContactListTools.getContactByNameOrPhoneNumber(editable.toString(), contacts);
                    addNewChip(contact, chipGroup);
                    inputText.setText("");
                }
            }
        });

    }

    private void addNewChip(Contact contact,  ViewGroup chipGroup) {
        Chip chip = new Chip(context);
        chip.setText(contact.getName());
        //chip.setChipIcon(ContextCompat.getDrawable(context, R.mipmap.ic_launcher_round));
        Drawable d = new BitmapDrawable(getResources(), ContactListTools.retrieveContactPhoto(context, contact.getName()));
        chip.setChipIcon(d);
        chip.setCloseIconVisible(true);
        //chip.setClickable(true);
        //chip.setCheckable(true);
        chipGroup.addView(chip, chipGroup.getChildCount() - 1);
        chip.setOnCloseIconClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<chipsContactsGroup.getChildCount();i++){
                    Chip ch = (Chip)chipsContactsGroup.getChildAt(i);
                    if(ch.getText().equals(chip.getText())){
                        ch.setChecked(false);
                    }
                }
                chipGroup.removeView(chip);
            }
        });
    }

    private void addChipsContact(List<Contact> contacts){
        for(Contact contact : contacts){

            Chip chip = new Chip(context);
            chip.setTag(contact);
            chip.setText(contact.getName());
            Drawable d = new BitmapDrawable(getResources(), ContactListTools.retrieveContactPhoto(context, contact.getName()));
            chip.setChipIcon(d);
            chip.setCloseIconVisible(false);
            chip.setClickable(true);
            chip.setCheckable(true);
            chipsContactsGroup.setSingleSelection(false);
            chipsContactsGroup.addView(chip, chipsContactsGroup.getChildCount() - 1);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(b){
                        addNewChip(contact, chipGroup);
                    }else{
                        for(int i=0;i<chipGroup.getChildCount();i++){
                            Chip ch = (Chip)chipGroup.getChildAt(i);
                            if(ch.getText().equals(chip.getText())){

                                chipGroup.removeView(ch);
                                return;
                            }
                        }
                    }
                }
            });
        }
    }
}
