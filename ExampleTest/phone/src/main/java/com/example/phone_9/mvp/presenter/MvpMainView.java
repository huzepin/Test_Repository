package com.example.phone_9.mvp.presenter;

/**
 * @author admin
 * @time 2019/8/13 11
 */
public interface MvpMainView extends MvpLoadingView {
    void showToash(String msg);

    void updateView();
}
