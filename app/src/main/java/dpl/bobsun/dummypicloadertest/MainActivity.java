package dpl.bobsun.dummypicloadertest;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import dpl.bobsun.dummypicloader.DummyPicLoader;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView) findViewById(R.id.id_grid_view);
        ArrayList pics = new ArrayList();
//        pics.addAll(Arrays.asList(
//                Environment.getExternalStorageDirectory().list(new FilenameFilter() {
//                    @Override
//                    public boolean accept(File file, String s) {
//                        if (s.endsWith(".jpg") || s.endsWith(".png") || s.endsWith(".bmp"))
//                            return true;
//                        return false;
//                    }
//                })));
        File[] files = Environment.getExternalStorageDirectory().listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String s) {
                        if (s.endsWith(".jpg") || s.endsWith(".png") || s.endsWith(".bmp"))
                            return true;
                        return false;
                    }
                });
        for (int i = 0; i < files.length; i++){
            pics.add(files[i].getPath());
        }
        GridAdapter adapter = new GridAdapter(this,0,pics);
        gridView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class GridAdapter extends ArrayAdapter{
        ArrayList list;
        public GridAdapter(Context context, int resource, ArrayList objects) {
            super(context, resource, objects);
            this.list = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ImageView ret = new ImageView(getContext());
            DummyPicLoader.getInstance(getContext()).resize(300,300).loadImage((String) list.get(position),ret,R.drawable.abc_ic_menu_paste_mtrl_am_alpha);
            return ret;
        }
    }
}
