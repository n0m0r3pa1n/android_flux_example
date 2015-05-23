package com.nmp90.androidfluxarchitecture.stores;

import com.google.gson.reflect.TypeToken;
import com.nmp90.androidfluxarchitecture.actions.AppsActions;
import com.nmp90.androidfluxarchitecture.events.ServerEvent;
import com.nmp90.androidfluxarchitecture.events.UiEvent;
import com.nmp90.androidfluxarchitecture.models.App;
import com.nmp90.androidfluxarchitecture.utils.BusProvider;
import com.nmp90.androidfluxarchitecture.utils.GsonInstance;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmp on 15-5-23.
 */
public class AppsBusStore extends Bus {
    public static final String TAG = AppsBusStore.class.getSimpleName();
    public AppsBusStore() {
        super(TAG);
        BusProvider.getInstance().register(this);
    }

    private List<App> apps = new ArrayList<>();
    public List<App> getData() {
        return apps;
    }

    private static AppsBusStore appsBusStore;
    private Method onAppsDataChangeListener;
    public static AppsBusStore getInstance() {
        if(appsBusStore == null) {
            appsBusStore = new AppsBusStore();
        }
        return appsBusStore;
    }

    @Subscribe
    public void receiveServerEvent(ServerEvent event) {
        //May be we should check if the operation is successful before get the data,
        //It can be used for operation which don't need to load data.
        switch(event.getAction()) {
            case AppsActions.LOAD_APPS:
                if(apps.size() == 0) {
                    apps = GsonInstance.getInstance().fromJson(event.getData(), new TypeToken<List<App>>() {
                    }.getType());
                } else {
                    List<App> newApps = GsonInstance.getInstance().fromJson(event.getData(), new TypeToken<List<App>>() {}.getType());
                    apps.addAll(newApps);
                }
                break;
            default:
                return;
        }

        this.post(new AppsLoadedEvent());
    }

    @Subscribe
    public void receiveUiEvent(UiEvent event) {

    }

    public class AppsLoadedEvent {

    }

}
