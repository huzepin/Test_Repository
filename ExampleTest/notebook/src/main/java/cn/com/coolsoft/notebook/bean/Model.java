package cn.com.coolsoft.notebook.bean;

import java.util.ArrayList;

public abstract class Model {
    private final ArrayList<PropertyChangedListener> listeners = new ArrayList<>();

    public void addListener(PropertyChangedListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PropertyChangedListener listener) {
        listeners.remove(listener);
    }

    public void notify(String propertyName) {
        for (PropertyChangedListener listener : listeners) {
            listener.onPropertyChanged(this, propertyName);
        }
    }



}
