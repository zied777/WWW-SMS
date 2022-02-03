package com.zied.nasri.www_sms.Tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

public class PermissionsTools {

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void checkPermission(Activity context, String[] permissions, int requestCodeAskPermission){
        boolean hasSmsPermission = hasPermissions(context,permissions);
        if (!hasSmsPermission) {

            /*
            if (!context.shouldShowRequestPermissionRationale(permission)) {
                showMessageOKCancel(context, "You need to allow access to Contacts",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                context.requestPermissions(permissions,requestCodeAskPermission);
                            }
                        });
            }

             */
            context.requestPermissions(permissions, requestCodeAskPermission);
        }

    }

    private static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
