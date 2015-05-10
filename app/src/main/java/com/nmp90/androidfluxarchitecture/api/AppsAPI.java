package com.nmp90.androidfluxarchitecture.api;

import android.util.Log;

import com.nmp90.androidfluxarchitecture.actions.AppsActions;
import com.nmp90.androidfluxarchitecture.events.ServerEvent;
import com.nmp90.androidfluxarchitecture.models.App;
import com.nmp90.androidfluxarchitecture.utils.BusProvider;
import com.nmp90.androidfluxarchitecture.utils.GsonInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmp on 15-5-10.
 */
public class AppsAPI {
    static int i = 0;
    public static void getApps() {
        List<App> apps = new ArrayList<App>();
        //This can be replaced with a callback
        //Post a string received from the server
        int nextApps = i + 10;
        for(; i<nextApps; i++) {
            Log.d("TAG", i + "");
            apps.add(new App("Test " + i));
        }
        i = nextApps;

        String serverResultString = GsonInstance.getInstance().toJson(apps);
        BusProvider.getInstance().postEvent(new ServerEvent(serverResultString, AppsActions.LOAD_APPS));
    }
}
