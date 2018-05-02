package com.example.storage.Bean;

import android.graphics.Bitmap;

/**
 * Created by Jerry on 2017/12/26.
 */
public class Photo {

    private Bitmap bitmap;

    public Photo() {
    }

    public Photo(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
