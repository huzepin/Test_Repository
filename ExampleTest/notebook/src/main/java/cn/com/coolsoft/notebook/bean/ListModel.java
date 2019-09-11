package cn.com.coolsoft.notebook.bean;

import java.util.ArrayList;

public class ListModel extends Model {
    private final ArrayList<ListChangedListener> listeners = new ArrayList<>();
    public ArrayList list = new ArrayList();

    public void addListListener(ListChangedListener listener) {
        listeners.add(listener);
    }

    public void removeListListener(ListChangedListener listener) {
        listeners.remove(listener);
    }

    public void notify(ListChangedKind kind, int index) {
        for (ListChangedListener listener : listeners) {
            listener.onListChanged(this, kind, index);
        }
    }

//    @SuppressWarnings("unchecked")
//    public <T> ArrayList<T> list() {
//        return (ArrayList<T>) list;
//    }

    public void setList(ArrayList list) {//!!!
        this.list = list;
    }



    public int itemCount() {
        return list.size();
    }

    @SuppressWarnings("unchecked")
    public <T> T item(int index) {
        return (T) list.get(index);
    }

    public int itemIndex(Object item) {
        return list.indexOf(item);
    }

    public void insertItem(int index, Object item) {
        list.add(index, item);
        notify(ListChangedKind.insert, index);
    }

    public void addItem(Object item) {
        insertItem(list.size(), item);
    }

    public void removeItem(int index) {
        list.remove(index);
        notify(ListChangedKind.remove, index);
    }

    public boolean removeItem(Object item) {
        int idx = list.indexOf(item);
        if (idx >= 0) {
            removeItem(idx);
            return true;
        }
        return false;
    }

    public void clearItems() {
        list.clear();
        notify(ListChangedKind.clear, -1);
    }

}
