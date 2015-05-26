package dpl.bobsun.dummypicloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by bobsun on 15-5-26.
 */
public class DPLTask extends AsyncTask<String, Integer, Bitmap> {

    private WeakReference<ImageView> imageViewWeakReference;

    public DPLTask(ImageView imageView){
        imageViewWeakReference = new WeakReference(imageView);
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return BitmapFactory.decodeFile(strings[0]);
    }

    @Override
    protected void onProgressUpdate(Integer... values){

    }

    @Override
    protected void onPostExecute(Bitmap result){
        if (imageViewWeakReference.get() != null)
            imageViewWeakReference.get().setImageBitmap(result);
    }

}
