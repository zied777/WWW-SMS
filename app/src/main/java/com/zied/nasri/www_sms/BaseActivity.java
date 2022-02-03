package com.zied.nasri.www_sms;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;

import com.zied.nasri.www_sms.Tools.PermissionsTools;
import com.zied.nasri.www_sms.core.components.permissions.IPermissionChecker;

public class BaseActivity extends AppCompatActivity {

    protected final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private IPermissionChecker iPermissionChecker;

    protected void checkReadSmsPermission(IPermissionChecker iPermissionChecker){

        this.setiPermissionChecker(iPermissionChecker);
        if(checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            iPermissionChecker.onPermissionGranted();
            return;
        }
        PermissionsTools.checkPermission(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_CONTACTS},REQUEST_CODE_ASK_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    if(iPermissionChecker != null)iPermissionChecker.onPermissionGranted();
                } else {
                    // Permission Denied
                    if(iPermissionChecker != null)iPermissionChecker.onPermissionDenied();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void setiPermissionChecker(IPermissionChecker iPermissionChecker) {
        this.iPermissionChecker = iPermissionChecker;
    }
}
