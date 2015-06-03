# DummyPicLoader
##Lightweight Async Image Loader Library for Android
Provide async bitmap loading from local filesystem & network.
###Introduction
DummyPicLoader provide async image loading & caching.

Currently, DPL provide two ways to load images, loading from file & loading from URL.

When calling loadImageFromFile, DPL will check memory cache first. When specified image is not exist in memory cache, it will decode the file using BitmapFactory in an AsyncTask.
 After passing bitmap to ImageView, it will write this bitmap into memory cache.
 If specified image is found in memory cache, it will passing it to ImageView directly.

When calling loadImageFromUrl, DPL will check disk cache first. When specified image is not exist in disk cache, it will download & put the image to disk cache.
 After that, DPL will load that file and put it to memory cache too.
 If specified image exists in disk cache, DPL will check memory cache, and the rest process is similar to loadImageFromFile.

__About cache:__

DPL provides two kind of cache, DPLDiskCache & DPLRamCache.

Consider them as a key-value collection. DPLDiskCache using __hash value__ of file name or URL location as its key word.
DPLRamCache using file name or URL location __directly__ as its key word.

Default memory cache capacity is `(Runtime.getRuntime().maxMemory() / 1024) / 8 ` KB.

Default disk cache directory is `/sdcard/data/DPLCache`.

You can change them as you wish using corresponding methods.

One more thing, currently DPL __do not__ provide disk cache management. So it __may cause cached images taking huge(not that huge) storage space__.
###Usage
####Basic
Load image from file:

`DummyPicLoader.getInstance(context).loadImageFromFile(fileName,imageView);`

Load image from URL:

`DummyPicLoader.getInstance(context).loadImageFromUrl(urlAddr,imageView);`
####Advanced
Set a holder image when loader is processing.

`DummyPicLoader.getInstance(getContext()).setDefaultImage(R.drawble.place_holder).loadImageFromFile(fileName,imageView);`

Resize picture.

`DummyPicLoader.getInstance(getContext()).resize(300,400).loadImageFromFile(fileName,imageView);`

etc, etc
####Performance
Haven't got time for a testing through by through. Current case is displaying 40+ wallpapers, average size 2MB, in a grid view on an emulator (4.4, Davik environment, 512M ram). Generally this test app will take 12M memory and didn't cause an OOM so far.
##To-do
~~Provide image ram cache.~~

~~Provide image local disk cache.~~

Documentation.

Test cases.
