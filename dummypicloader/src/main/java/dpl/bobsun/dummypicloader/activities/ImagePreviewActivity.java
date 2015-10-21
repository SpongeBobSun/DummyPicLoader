package dpl.bobsun.dummypicloader.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import dpl.bobsun.dummypicloader.R;
import dpl.bobsun.dummypicloader.adapters.ImageViewPagerAdapter;


/**
 * Created by bob.sun on 15/7/28.
 */
public class ImagePreviewActivity extends AppCompatActivity {
    private ArrayList images;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepreviewactivity);
        images = getIntent().getStringArrayListExtra("image_list");
        int index = getIntent().getIntExtra("start_image", 0);
        setupViewPager(index);
//        DummyPicLoader.getInstance(this.getApplicationContext()).loadImageFromFile(getIntent().getStringExtra("image_file_path"), (ImageView) findViewById(R.id.id_imageview));
    }

    private void setupViewPager(int index){
        ViewPager viewPager = (ViewPager) findViewById(R.id.id_viewpager_images);
        ImageViewPagerAdapter imageViewPagerAdapter = new ImageViewPagerAdapter(getSupportFragmentManager());
        imageViewPagerAdapter.setImages(images);
        viewPager.setAdapter(imageViewPagerAdapter);
        viewPager.setCurrentItem(index);
    }
}
