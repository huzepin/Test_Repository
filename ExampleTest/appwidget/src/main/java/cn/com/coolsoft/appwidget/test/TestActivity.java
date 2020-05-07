package cn.com.coolsoft.appwidget.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.net.ssl.SNIHostName;

import cn.com.coolsoft.appwidget.R;
import cn.com.coolsoft.appwidget.mediaplayer.MediaPlayerActivity;
import cn.com.coolsoft.appwidget.view.ActionSheet;

/**
 * @author admin
 * @time 2019/10/14 16
 */
public class TestActivity extends Activity implements View.OnClickListener {
    Button snack_bar,bd_snackview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        snack_bar = findViewById(R.id.bt_snackbar);
        snack_bar.setOnClickListener(this);
        bd_snackview = findViewById(R.id.bd_snackview);
        bd_snackview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bd_snackview){
            ActionSheet.makeActionSheet(bd_snackview,R.layout.custom_action_sheet,"添加图片").show()
                    .registerItemClick(new ActionSheet.IItemClick() {
                        @Override
                        public void onItemClick(int index) {
                            Toast.makeText(TestActivity.this,"第"+index+"个按钮", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else if (v == snack_bar) {
            Snackbar.make(v, "这是底部淡出来的", Snackbar.LENGTH_LONG).setAction("QUENT", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(TestActivity.this, "11", Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
    }
}
