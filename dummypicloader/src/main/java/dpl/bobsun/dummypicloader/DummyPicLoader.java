package dpl.bobsun.dummypicloader;

import android.content.Context;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URL;

/**
 * Created by bobsun on 15-5-26.
 */
public class DummyPicLoader {
    Context context;
    private int width, height;
    private boolean bmpSet;
    private Bitmap defaultBitmap = null;
    private BitmapFactory.Options options;
    private DummyPicLoader(Context context){
        this.context = context;
        width = 0;
        height = 0;
        options = new BitmapFactory.Options();
    }
    private Context getContext(){
        return context;
    }

    public static DummyPicLoader getInstance(Context context){
        return new DummyPicLoader(context);
    }

    public void loadImageFromFile(String fileName,
                                            ImageView imageView){
        bmpSet = true;
        if (imageView.getDrawable() != null && imageView.getDrawable() instanceof DPLDrawable){
            ((DPLDrawable) imageView.getDrawable()).getTask().cancel(true);
        }
        DPLTask task = new DPLTask(imageView,DPLTask.TASK_TYPE_FILE);
        task.setOptions(options);
        DPLDrawable drawable;
        if (defaultBitmap == null){
            drawable = new DPLDrawable(getContext().getResources(),fileName,task);
        }else {
            drawable = new DPLDrawable(getContext().getResources(),defaultBitmap,task);
        }
        imageView.setImageDrawable(drawable);

        task.execute(fileName);
        return;
    }

    public void loadImageFromUrl(String urlAddr,ImageView imageView){
        DPLTask task = new DPLTask(imageView,DPLTask.TASK_TYPE_URL);

        task.setOptions(options);

        DPLDrawable drawable;
        if (defaultBitmap == null){
            drawable = new DPLDrawable(getContext().getResources(),urlAddr,task);
        }else {
            drawable = new DPLDrawable(getContext().getResources(),defaultBitmap,task);
        }
        imageView.setImageDrawable(drawable);
        task.execute(urlAddr);
        return;
    }

    public DummyPicLoader setQuality(){
        checkBitmapState();
        return this;
    }
    public DummyPicLoader resize(int width, int height){
        checkBitmapState();
        options.outWidth = width;
        options.outHeight = height;

        return this;
    }


    public DummyPicLoader roundCorner(float radius){
        checkBitmapState();
        return this;
    }

    private void checkBitmapState(){
        if (bmpSet){
            throw new IllegalStateException("Bitmap alread set!");
        }
    }

    public DummyPicLoader setDefaultImage(int defaultResId){
        checkBitmapState();
        if (defaultBitmap == null)
            defaultBitmap = BitmapFactory.decodeResource(context.getResources(),defaultResId);
        return this;
    }
}
