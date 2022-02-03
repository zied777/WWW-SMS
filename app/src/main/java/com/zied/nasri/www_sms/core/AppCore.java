package com.zied.nasri.www_sms.core;

import com.zied.nasri.www_sms.core.components.screens.IScreen;

public class AppCore {

    private static AppCore core;
    private IScreen iScreen;

    private AppCore(){}

    public static AppCore getInstance(){
        if(core==null){
            core=new AppCore();
        }
        return core;
    }



}
