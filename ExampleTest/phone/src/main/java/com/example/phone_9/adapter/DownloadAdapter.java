package com.example.phone_9.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import com.example.phone_9.DownloadManager;
import com.example.phone_9.MultipleActivity;

import com.example.phone_9.R;
import com.example.phone_9.bean.DownloadInfo;

import java.util.List;

/**
 * Created by zs
 * Date：2018年 09月 11日
 * Time：18:06
 * —————————————————————————————————————
 * About:
 * —————————————————————————————————————
 */
public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.UploadHolder>  {

    private List<DownloadInfo> mdata;
    private MultipleActivity mcontext;

    public DownloadAdapter(List<DownloadInfo> mdata, MultipleActivity context) {
        this.mdata = mdata;
        this.mcontext = context;
    }

    /**
     * 更新下载进度
     * @param info
     */
    public void updateProgress(DownloadInfo info){
        for (int i = 0; i < mdata.size(); i++){
            if (mdata.get(i).getUrl().equals(info.getUrl())){
                mdata.set(i,info);
                notifyItemChanged(i);
                break;
            }
        }
    }

    @Override
    public UploadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_download_layout,null);
        return new UploadHolder(view);
    }

    @Override
    public void onBindViewHolder(UploadHolder holder, int position) {
        final DownloadInfo info = mdata.get(position);
        if (DownloadInfo.DOWNLOAD_CANCEL.equals(info.getDownloadStatus())){
            holder.main_progress.setProgress(0);
        }else if (DownloadInfo.DOWNLOAD_OVER.equals(info.getDownloadStatus())){
            holder.main_progress.setProgress(holder.main_progress.getMax());
        }else {
            if (info.getTotal() == 0){
                holder.main_progress.setProgress(0);
            }else {
                float d = info.getProgress() * holder.main_progress.getMax() / info.getTotal();
                holder.main_progress.setProgress((int) d);
            }
        }
        holder.main_btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance().download(info.getUrl(),10,mcontext);
            }
        });

        holder.main_btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance().pauseDownload(info.getUrl(),11,mcontext);
            }
        });

        holder.main_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.getInstance().cancelDownload(info,12,mcontext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class UploadHolder extends RecyclerView.ViewHolder{

        private ProgressBar main_progress;
        private Button main_btn_down;
        private Button main_btn_pause;
        private Button main_btn_cancel;

        public UploadHolder(View itemView) {
            super(itemView);
            main_progress = itemView.findViewById(R.id.main_progress);
            main_btn_down = itemView.findViewById(R.id.main_btn_down);
            main_btn_pause = itemView.findViewById(R.id.main_btn_pause);
            main_btn_cancel = itemView.findViewById(R.id.main_btn_cancel);
        }
    }

}
