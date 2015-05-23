package com.nmp90.androidfluxarchitecture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.nmp90.androidfluxarchitecture.api.AppsAPI;
import com.nmp90.androidfluxarchitecture.models.App;
import com.nmp90.androidfluxarchitecture.stores.AppsBusStore;
import com.nmp90.androidfluxarchitecture.stores.BaseStore;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements BaseStore.OnChangeListener {
    private ListView lvApps;
    private Button btnLoadApps;
    private Button btnLoadAppsFromComponent;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: If you want to try it with listeners uncomment this
        //AppsStore.getInstance().addOnChangeListener(this);
        AppsBusStore.getInstance().register(this);
        btnLoadAppsFromComponent = (Button) findViewById(R.id.btn_load_apps_component);
        btnLoadAppsFromComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ComponentActivity.class);
                startActivity(i);
            }
        });

        btnLoadApps = (Button) findViewById(R.id.btn_load_apps);
        btnLoadApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppsAPI.getApps();
            }
        });
        lvApps = (ListView) findViewById(R.id.lv_apps);
    }

    @Subscribe
    public void onAppsDataChange(AppsBusStore.AppsLoadedEvent event) {
        onChange();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO: If you want to try it with listeners uncomment this
        //AppsStore.getInstance().removeOnChangeListener(this);
        AppsBusStore.getInstance().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChange() {
        Toast.makeText(this, "Apps loaded, scroll down", Toast.LENGTH_SHORT).show();
        //TODO: Replace this with AppsStore if you want to use listeners
        List<App> apps = AppsBusStore.getInstance().getData();
        List<String> appNames = new ArrayList<String>();
        for(App app : apps) {
            appNames.add(app.getName());
        }
        if(adapter == null) {
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, appNames);
            lvApps.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(appNames);
            adapter.notifyDataSetChanged();
        }

    }
}
