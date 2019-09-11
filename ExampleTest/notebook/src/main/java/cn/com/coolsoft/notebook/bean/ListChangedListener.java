package cn.com.coolsoft.notebook.bean;

/**
 * @author admin
 * @time 2019/9/9 10
 */
public interface ListChangedListener {
    void onListChanged(ListModel model, ListChangedKind kind, int index);
}
