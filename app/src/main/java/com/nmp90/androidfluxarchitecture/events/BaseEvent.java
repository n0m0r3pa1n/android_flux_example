package com.nmp90.androidfluxarchitecture.events;

/**
 * Created by nmp on 15-5-10.
 */
public abstract class BaseEvent {
    private String data;
    private String action;

    private boolean isSuccess;
    private String errorMessage;

    public BaseEvent(String data, String action) {
        this.data = data;
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    protected abstract ActionTypes getEventActionType();

    public enum ActionTypes {
        SERVER_ACTION,
        VIEW_ACTION
    }
}
