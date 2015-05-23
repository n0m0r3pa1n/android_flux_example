package com.nmp90.androidfluxarchitecture.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nmp90.androidfluxarchitecture.R;
import com.nmp90.androidfluxarchitecture.models.App;
import com.nmp90.androidfluxarchitecture.stores.AppsBusStore;
import com.nmp90.androidfluxarchitecture.stores.BaseStore;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmp on 15-5-10.
 */
public class AppsList extends LinearLayout implements BaseStore.OnChangeListener {
    private LayoutInflater inflater;
    private ListView lvAppsList;
    private ArrayAdapter adapter;

    public AppsList(Context context) {
        super(context);
        if(!isInEditMode()) {
            init(context);
        }
    }

    public AppsList(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()) {
            init(context);
        }
    }

    public AppsList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode()) {
            init(context);
        }
    }

    public void init(Context context) {
        if(inflater == null) {
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        View view = inflater.inflate(R.layout.view_apps_list, this, true);
        lvAppsList = (ListView) view.findViewById(R.id.lv_apps_list);
        //TODO: Replace this with AppsStore if you want to use listeners
        List<App> apps = AppsBusStore.getInstance().getData();
        if(apps != null && apps.size() > 0) {
            onChange();
        }

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //TODO: If you want to try it with listeners uncomment this
        //AppsStore.getInstance().addOnChangeListener(this);
        AppsBusStore.getInstance().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //TODO: If you want to try it with listeners uncomment this
        //AppsStore.getInstance().removeOnChangeListener(this);
        AppsBusStore.getInstance().unregister(this);
    }

    @Subscribe
    public void onAppsLoaded(AppsBusStore.AppsLoadedEvent event) {
        onChange();
    }

    @Override
    public void onChange() {
        Toast.makeText(getContext(), "Apps loaded, scroll down", Toast.LENGTH_SHORT).show();
        //TODO: Replace this with AppsStore if you want to use listeners
        List<App> apps = AppsBusStore.getInstance().getData();
        List<String> appNames = new ArrayList<String>();
        for(App app : apps) {
            appNames.add(app.getName());
        }

        if(adapter == null) {
            adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, appNames);
            lvAppsList.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(appNames);
            adapter.notifyDataSetChanged();
        }

    }
}
