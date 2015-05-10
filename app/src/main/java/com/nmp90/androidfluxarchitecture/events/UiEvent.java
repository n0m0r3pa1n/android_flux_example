package com.nmp90.androidfluxarchitecture.events;

/**
 * Created by nmp on 15-5-10.
 */
public class UiEvent extends BaseEvent{
    public UiEvent(String data, String action) {
        super(data, action);
    }

    @Override
    protected ActionTypes getEventActionType() {
        return ActionTypes.VIEW_ACTION;
    }
}
