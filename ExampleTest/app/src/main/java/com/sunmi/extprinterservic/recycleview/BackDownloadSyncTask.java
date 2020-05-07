package com.sunmi.extprinterservic.recycleview;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

/**
 * @author admin
 * @time 2019/9/24 10
 */
public class BackDownloadSyncTask extends AsyncTaskLoader<String> {

    public BackDownloadSyncTask(@NonNull Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (takeContentChanged()){
            forceLoad();
        }
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return null;
    }
}
