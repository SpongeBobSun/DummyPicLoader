package dpl.bobsun.dummypicloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * Created by bobsun on 15-5-26.
 */
public class DummyPicLoader {
    Context context;
    private int width, height;
    private DummyPicLoader(Context context){
        this.context = context;
        width = 0;
        height = 0;
    }
    private Context getContext(){
        return context;
    }

    public static DummyPicLoader getInstance(Context context){
        return new DummyPicLoader(context);
    }

    public DummyPicLoader loadImage(String fileName,
                          ImageView imageView,
                          int resId){
        if (imageView.getDrawable() != null && imageView.getDrawable() instanceof DPLDrawable){
            ((DPLDrawable) imageView.getDrawable()).getTask().cancel(true);
        }
        DPLTask task = new DPLTask(imageView);
        DPLDrawable drawable = new DPLDrawable(getContext().getResources(),fileName,task);
        imageView.setImageDrawable(drawable);
        task.execute(fileName);
        return this;
    }

    public DummyPicLoader resize(int width, int height){

        return this;
    }


    public DummyPicLoader roundCorner(float radius){

        return this;
    }

}
