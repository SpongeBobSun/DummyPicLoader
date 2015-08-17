package dpl.bobsun.dummypicloader.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import dpl.bobsun.dummypicloader.DummyPicLoader;
import dpl.bobsun.dummypicloader.utils.DisplayUtil;


/**
 * Created by bob.sun on 15/8/7.
 */
public class ImageViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList images;
    public ImageViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setImages(ArrayList images){
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment ret = new ImageFragment();
        ret.setImage((String) images.get(position));
        return ret;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    class ImageFragment extends Fragment {
        private String image;
        public ImageFragment(){
            super();
        }
        public void setImage(String filePath){
            image = filePath;
        }
        @Override
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup parent, Bundle savedInstanceState){
            View ret = new ImageView(this.getActivity());
            DummyPicLoader.getInstance(this.getActivity()).resize(DisplayUtil.getStaticInstance(null).getScreenWidth(),DisplayUtil.getStaticInstance(null).getScaledHeight()).loadImageFromFile(image, (ImageView) ret);
            return ret;
        }
    }
}
