package dpl.bobsun.dummypicloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by bobsun on 15-5-26.
 */
public class DPLTask extends AsyncTask<String, Integer, Bitmap> {

    private WeakReference<ImageView> imageViewWeakReference;
    BitmapFactory.Options options;

    public DPLTask(ImageView imageView){
        imageViewWeakReference = new WeakReference(imageView);
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(strings[0]);
            fileInputStream.getFD();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Bitmap.createBitmap(300,300,null);
        } catch (IOException e) {
            e.printStackTrace();
            return Bitmap.createBitmap(300,300,null);
        }
        return BitmapFactory.decodeStream(fileInputStream,new Rect(0,0,options.outWidth,options.outHeight),options);
    }

    @Override
    protected void onProgressUpdate(Integer... values){

    }

    @Override
    protected void onPostExecute(Bitmap result){
        if (isCancelled()){
            result = null;
            return;
        }
        if (imageViewWeakReference != null) {
            final ImageView imageView = imageViewWeakReference.get();
            final DPLTask dplTask =
                    getBitmapWorkerTask(imageView);
            if (this == dplTask && imageView != null) {
                imageView.setImageBitmap(result);
            }
        }

    }

    private static DPLTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof DPLDrawable) {
                final DPLDrawable asyncDrawable = (DPLDrawable) drawable;
                return asyncDrawable.getTask();
            }
        }
        return null;
    }

    public void setOptions(BitmapFactory.Options options,String fileName){
        if (options.outWidth !=0 && options.outHeight !=0){
            BitmapFactory.Options fakeOption = new BitmapFactory.Options();
            fakeOption.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName,fakeOption);
            if ( fakeOption.outWidth / options.outWidth > fakeOption.outHeight / options.outHeight){
                options.inSampleSize =fakeOption.outWidth / options.outWidth;
                options.inScaled = true;
            }
            if ( fakeOption.outWidth / fakeOption.outWidth < fakeOption.outHeight / options.outHeight) {
                options.inSampleSize = fakeOption.outHeight / options.outHeight;
                options.inScaled = true;
            }
        }
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        this.options = options;
    }

}
