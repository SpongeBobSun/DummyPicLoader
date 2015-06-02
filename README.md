# DummyPicLoader
##Lightweight Async Image Loader Library for Android
Provide async bitmap loading form local filesystem & network. (Developing & Testing)
###Usage.
####Basic.
Load image from file:

`DummyPicLoader.getInstance(context).loadImageFromFile(fileName,imageView);`

Load image from URL:

`DummyPicLoader.getInstance(context).loadImageFromUrl(urlAddr,imageView);`
####Advanced.
Set a holder image when loader is processing.

`DummyPicLoader.getInstance(getContext()).setDefaultImage(R.drawble.place_holder).loadImageFromFile(fileName,imageView);`

Resize picture.

`DummyPicLoader.getInstance(getContext()).resize(300,400)..loadImageFromFile(fileName,imageView);`
##To-do
~~Provide image ram cache.~~

~~Provide image local disk cache.~~

Documentation.
