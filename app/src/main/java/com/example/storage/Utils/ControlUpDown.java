package com.example.storage.Utils;

import android.view.View;
import android.widget.ImageButton;

import com.example.storage.R;

/**
 * Created by Jerry on 2017/11/23.
 */
public class ControlUpDown {

    public static boolean flag = true;

    public static boolean control(ImageButton button,int pic1,int pic2,ImageButton button2,ImageButton button3,ImageButton button4) {
        if (button.getVisibility() == View.GONE) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.GONE);
            button2.setBackgroundResource(pic1);
            button3.setVisibility(View.GONE);
            button3.setBackgroundResource(pic1);
            button4.setVisibility(View.GONE);
            button4.setBackgroundResource(pic1);
            flag = true;
        } else {
            if (flag) {
                button.setBackgroundResource(pic2);
                flag = false;
            } else {
                button.setBackgroundResource(pic1);
                flag = true;
            }
        }
        return flag;
    }

    public static boolean control(ImageButton button,int pic1,int pic2,ImageButton button2,ImageButton button3,ImageButton button4,ImageButton button5,ImageButton button6) {
        if (button.getVisibility() == View.GONE) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.GONE);
            button2.setBackgroundResource(pic1);
            button3.setVisibility(View.GONE);
            button3.setBackgroundResource(pic1);
            button4.setVisibility(View.GONE);
            button4.setBackgroundResource(pic1);
            button5.setVisibility(View.GONE);
            button5.setBackgroundResource(pic1);
            button6.setVisibility(View.GONE);
            button6.setBackgroundResource(pic1);
            flag = true;
        } else {
            if (flag) {
                button.setBackgroundResource(pic2);
                flag = false;
            } else {
                button.setBackgroundResource(pic1);
                flag = true;
            }
        }
        return flag;
    }

    public static Boolean control(ImageButton button,int pic1,int pic2,ImageButton button2,ImageButton button3,ImageButton button4,ImageButton button5,ImageButton button6,ImageButton button7) {
        if (button.getVisibility() == View.GONE) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.GONE);
            button2.setBackgroundResource(pic1);
            button3.setVisibility(View.GONE);
            button3.setBackgroundResource(pic1);
            button4.setVisibility(View.GONE);
            button4.setBackgroundResource(pic1);
            button5.setVisibility(View.GONE);
            button5.setBackgroundResource(pic1);
            button6.setVisibility(View.GONE);
            button6.setBackgroundResource(pic1);
            button7.setVisibility(View.GONE);
            button7.setBackgroundResource(pic1);
            flag = true;
        } else {
            if (flag) {
                button.setBackgroundResource(pic2);
                flag = false;
            } else {
                button.setBackgroundResource(pic1);
                flag = true;
            }
        }
        return flag;
    }

    public static boolean control(ImageButton button,int pic1,int pic2,ImageButton button2,ImageButton button3) {
        if (button.getVisibility() == View.GONE) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.GONE);
            button2.setBackgroundResource(pic1);
            button3.setVisibility(View.GONE);
            button3.setBackgroundResource(pic1);
            flag = true;
        } else {
            if (flag) {
                button.setBackgroundResource(pic2);
                flag = false;
            } else {
                button.setBackgroundResource(pic1);
                flag = true;
            }
        }
        return flag;
    }

}
