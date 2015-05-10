package com.nmp90.androidfluxarchitecture.utils;

import com.squareup.otto.Bus;

/**
 * Created by nmp on 15-5-10.
 */
public class BusProvider {
    private static BusProvider busProvider;
    private Bus bus = new Bus();
    private BusProvider() {

    }

    public static BusProvider getInstance() {
        if(busProvider == null) {
            busProvider = new BusProvider();
        }

        return busProvider;
    }

    public void postEvent(Object object) {
        bus.post(object);
    }

    public void register(Object object) {
        bus.register(object);
    }

    public void unregister(Object object) {
        bus.unregister(object);
    }


}
