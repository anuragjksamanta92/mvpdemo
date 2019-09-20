package com.basu.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoLoader {

    public static Target picassoImageTarget(Context context, final String imageDir, final String imageName) {

        ContextWrapper cw = new ContextWrapper(context);
        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
        Log.e("DIR",""+directory);
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final File myImageFile = new File(directory, imageName); // Create image file
                        Log.e("myImageFile",""+myImageFile);
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(myImageFile);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } /*finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }*/
                        Log.e("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {}
            }
        };
    }

    public static File getFileFromLocal(Context context, final String filename){

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(AppConstants.LOCAL_SPLASH_DIR_PATH, Context.MODE_PRIVATE);
        File myImageFile = new File(directory, filename+".jpeg");
        return myImageFile;
    }


    public static boolean checkIfSplashDownloaded(Context mContext, String event_id) {
        boolean isExists = false;
        ContextWrapper cw = new ContextWrapper(mContext);
        File directory = cw.getDir(AppConstants.LOCAL_SPLASH_DIR_PATH, Context.MODE_PRIVATE);
        File myImageFile = new File(directory, event_id+".jpeg");
        if(myImageFile.exists()){
            isExists =  true;
        }
        return isExists;
    }
}