package com.zied.nasri.www_sms.Tools;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.zied.nasri.www_sms.R;

public class DialogTools {

    public static void showMessageDialog(Context context, String okTitle, DialogInterface.OnClickListener positiveClickListener){

        View view = LayoutInflater.from(context).inflate(R.layout.simple_dialog_message_layout, null);
        new MaterialAlertDialogBuilder(context)
                .setTitle("Title")
                .setView(view)
                .setPositiveButton("ok", positiveClickListener)
        .show();
    }
}
