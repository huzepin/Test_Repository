package cn.com.coolsoft.notebook.bean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;


public abstract class ItemHolder extends RecyclerView.ViewHolder implements PropertyChangedListener{
    ListModel listModel;
    private Object item;
    public ItemHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends ListModel> T listModel() {
        return (T) listModel;
    }

    @SuppressWarnings("unchecked")
    public <T> T item() {
        return (T) item;
    }

    private void clearItem() {
        if (item instanceof Model) {
            Model model = (Model) item;
            model.removeListener(this);
        }
        item = null;
    }

    public void onBind(Object item) {
        clearItem();
        if (item instanceof Model) {
            Model model = (Model) item;
            model.addListener(this);
        }
        this.item = item;
    }

    public void onUnBind() {
        clearItem();
    }



    @Override
    public void onPropertyChanged(Model model, String propertyName) {

    }


}
