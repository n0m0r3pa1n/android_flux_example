package com.nmp90.androidfluxarchitecture.stores;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmp on 15-5-10.
 */
public abstract class BaseStore {
    private List<OnChangeListener> onChangeListeners = new ArrayList<OnChangeListener>();
    public void addOnChangeListener(OnChangeListener listener) {
        onChangeListeners.add(listener);
    }

    public void removeOnChangeListener(OnChangeListener listener) {
        onChangeListeners.remove(listener);
    }

    public void onChange() {
        int size = onChangeListeners.size();
        for(int i=0; i < size; i++) {
            OnChangeListener listener = onChangeListeners.get(i);
            listener.onChange();
        }
    }

    public interface OnChangeListener {
        void onChange();
    }
}
