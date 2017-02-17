package tydic.cn.com.stickynavproject;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {
    private Button btnOver;
    private Button btnPhotoChoose;
    private boolean isOver= false;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 0x123:
                   btnOver.setText((String)msg.obj);
                   break;
               case 0x456:
                   if (!isOver){
                       Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                       startActivity(intent);
                       finish();
                   }
           }

        }
    };
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btnOver = (Button) findViewById(R.id.btn_over);
        btnPhotoChoose = (Button) findViewById(R.id.btn_photo_choose);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i=3;i>0;i--){
                    try {
                        Message message = new Message();
                        message.what = 0x123;
                        message.obj = String.format(getApplicationContext().getResources().getString(R.string.over_jump),i.toString());
                        mHandler.sendMessage(message);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mHandler.sendEmptyMessage(0x456);
            }
        });


        btnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                isOver= true;
                mHandler.removeMessages(0x123);
                mHandler.removeMessages(0x456);
            }
        });
        btnPhotoChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this,PhotoChooseActivity.class);
                startActivity(intent);
                finish();
                isOver= true;
                mHandler.removeMessages(0x123);
                mHandler.removeMessages(0x456);

            }
        });
        thread.start();
    }
}
