package dpl.bobsun.dummypicloader.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;

import java.util.ArrayList;

import dpl.bobsun.dummypicloader.R;
import dpl.bobsun.dummypicloader.adapters.ImageAdapter;
import dpl.bobsun.dummypicloader.utils.DisplayUtil;


/**
 * Created by bob.sun on 15/8/5.
 */
public class ImageChooseActivity extends AppCompatActivity {
    GridView gridView;
    private ImageAdapter imageAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DisplayUtil.getStaticInstance(this);
        setContentView(R.layout.activity_imagechoose);
        gridView = (GridView) findViewById(R.id.id_image_gridview);
        gridView.setNumColumns(3);
        imageAdapter = new ImageAdapter(this,R.layout.layout_image_cell);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.id_image_selected);
                if (checkBox.isChecked()){
                    imageAdapter.removeSelectedImage(position);
                } else {
                    imageAdapter.addSelectedImage(position);
                }
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
        setupActionBar();
    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        View view = View.inflate(this,R.layout.actionbar_imagechoose,null);
        view.findViewById(R.id.id_actionbar_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageResult();
                finish();
            }
        });
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(view);

    }

    @Override
    public void onBackPressed(){
        setImageResult();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                setImageResult();
                finish();
                return true;
            default:
                return false;
        }
    }

    private void setImageResult(){
        ArrayList result = imageAdapter.getSelectedImages();
        if (result.size() == 0){
            setResult(RESULT_CANCELED, null);
        } else {
            Intent data = new Intent();
            data.putStringArrayListExtra("images",result);
            setResult(RESULT_OK, data);
        }
    }
}
