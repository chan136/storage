package com.example.storage.Utils;

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

/**
 * Created by Jerry on 2017/11/22.
 */
public class DrawableColor {

    public static void changeColor (View view, int ButtonId, int Drawable, int color) {
        ImageButton button = (ImageButton)view.findViewById(ButtonId);
        Drawable icon = view.getResources().getDrawable(Drawable);
        Drawable tintIcon = DrawableCompat.wrap(icon);
        DrawableCompat.setTintList(tintIcon, view.getResources().getColorStateList(color));
        button.setBackground(tintIcon);
    }

    public static void changeColor (View view, RadioButton button, int Drawable, int color) {
        Drawable icon = view.getResources().getDrawable(Drawable);
        Drawable tintIcon = DrawableCompat.wrap(icon);
        DrawableCompat.setTintList(tintIcon, view.getResources().getColorStateList(color));
        button.setBackground(tintIcon);
    }

    public static void changeColor (View view, MenuItem item, int Drawable, int color) {
        Drawable icon = view.getResources().getDrawable(Drawable);
        Drawable tintIcon = DrawableCompat.wrap(icon);
        DrawableCompat.setTintList(tintIcon, view.getResources().getColorStateList(color));
        item.setIcon(tintIcon);
    }

}
