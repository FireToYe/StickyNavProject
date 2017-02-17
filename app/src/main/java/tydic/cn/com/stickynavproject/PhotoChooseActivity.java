package tydic.cn.com.stickynavproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;

import java.util.ArrayList;
import java.util.List;

public class PhotoChooseActivity extends AppCompatActivity {
    private Button btn;
    private ImageView ivChoogePhoto;
    private static final int COMPRESS_REQUEST_CODE = 2048;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_choose);
        btn = (Button) findViewById(R.id.btn_choose);
        ivChoogePhoto = (ImageView) findViewById(R.id.iv_choose_photo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoxingConfig singleImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG);
                Boxing.of(singleImgConfig).withIntent(PhotoChooseActivity.this, BoxingActivity.class).start(PhotoChooseActivity.this, COMPRESS_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            List<BaseMedia> medias = Boxing.getResult(data);
            if (requestCode == COMPRESS_REQUEST_CODE) {
                List<BaseMedia> baseMediaList = new ArrayList<>(1);
                BaseMedia baseMedia = medias.get(0);
                if (!(baseMedia instanceof ImageMedia)) {
                    return;
                }
                ImageMedia imageMedia = (ImageMedia) baseMedia;
                String path=imageMedia.getThumbnailPath();
                BoxingMediaLoader.getInstance().displayThumbnail(ivChoogePhoto,path,250,250);

            }
        }
    }
}
