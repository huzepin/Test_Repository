package cn.com.coolsoft.appwidget.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

import cn.com.coolsoft.appwidget.R;
import cn.com.coolsoft.appwidget.view.ActionSheet;

/**
 * @author admin
 * @time 2019/10/22 10
 */
public class MediaPlayerActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener,SurfaceHolder.Callback {

    private Display currDisplay;
     private SurfaceView surfaceView;
     private SurfaceHolder holder;
     private MediaPlayer player;
     private int vWidth,vHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediaplayer);
        surfaceView = this.findViewById(R.id.sfv);

        String path = Environment.getExternalStorageDirectory().getPath()+"CoolSeft/guanggao.mp4";
        //给SurfaceView添加CallBack监听
                holder = surfaceView.getHolder();
                holder.addCallback(this);
                //为了可以播放视频或者使用Camera预览，我们需要指定其Buffer类型
                holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

                //下面开始实例化MediaPlayer对象
                player = new MediaPlayer();
                player.setOnCompletionListener(this);
                player.setOnErrorListener(this);
                player.setOnInfoListener(this);
                player.setOnPreparedListener(this);
                player.setOnSeekCompleteListener(this);
                player.setOnVideoSizeChangedListener(this);
                Log.v("Begin:::", "surfaceDestroyed called");
                //然后指定需要播放文件的路径，初始化MediaPlayer
                String dataPath = Environment.getExternalStorageDirectory().getPath()+"CoolSeft/guanggao.mp4";
                try {
                         player.setDataSource(dataPath);
                         Log.v("Next:::", "surfaceDestroyed called");
                     } catch (IllegalArgumentException e) {
                         e.printStackTrace();
                     } catch (IllegalStateException e) {
                         e.printStackTrace();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }

                 //然后，我们取得当前Display对象
                 currDisplay = this.getWindowManager().getDefaultDisplay();




    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 当SurfaceView中的Surface被创建的时候被调用
         //在这里我们指定MediaPlayer在当前的Surface中进行播放
         player.setDisplay(holder);
         //在指定了MediaPlayer播放的容器后，我们就可以使用prepare或者prepareAsync来准备播放了
         player.prepareAsync();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 当Surface尺寸等参数改变时触发
              Log.v("Surface Change:::", "surfaceChanged called");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        // 当MediaPlayer播放完成后触发
               Log.v("Play Over:::", "onComletion called");
               this.finish();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.v("Play Error:::", "onError called");
         switch (what) {
             case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                     Log.v("Play Error:::", "MEDIA_ERROR_SERVER_DIED");
                     break;
             case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                     Log.v("Play Error:::", "MEDIA_ERROR_UNKNOWN");
                     break;
             default:
                     break;
             }
         return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        // 当一些特定信息出现或者警告时触发
         switch(what){
             case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                     break;
             case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:
                     break;
             case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                     break;
             case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                     break;
             }
         return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // 当prepare完成后，该方法触发，在这里我们播放视频

          //首先取得video的宽和高
          vWidth  = player.getVideoWidth();
          vHeight = player.getVideoHeight();

          if (vWidth > currDisplay.getWidth() || vHeight > currDisplay.getHeight()) {
                  //如果video的宽或者高超出了当前屏幕的大小，则要进行缩放
                  float wRatio = (float)vWidth/(float)currDisplay.getWidth();
                  float hRatio = (float)vHeight/(float)currDisplay.getHeight();

                  //选择大的一个进行缩放
                  float ratio = Math.max(wRatio, hRatio);

                  vWidth = (int)Math.ceil((float)vWidth/ratio);
                  vHeight = (int)Math.ceil((float)vHeight/ratio);

                  //设置surfaceView的布局参数
                  surfaceView.setLayoutParams(new LinearLayout.LayoutParams(vWidth, vHeight));

                  //然后开始播放视频

                  player.start();
              }
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        // seek操作完成时触发
                Log.v("Seek Completion", "onSeekComplete called");
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
// 当video大小改变时触发
               //这个方法在设置player的source后至少触发一次
               Log.v("Video Size Change", "onVideoSizeChanged called");
    }
}
