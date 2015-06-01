package dpl.bobsun.dummypicloader.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by bobsun on 15-6-1.
 */
public class DPLRamCache {
    private static DPLRamCache staticInstance;
    LruCache<String, Bitmap> cache ;
    private DPLRamCache(){

    }

    public static DPLRamCache getStaticInstance(){
        if (staticInstance == null){
            staticInstance = new DPLRamCache();
        }
        return staticInstance;
    }

    public void put(String tag, Bitmap bitmap){

    }

    public Bitmap get(String tag){
        Bitmap ret = null;
        return ret;
    }

}
