package com.example.storage.Utils;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;

import com.example.storage.R;

/**
 * Created by Jerry on 2017/11/27.
 */
public class EditTextPic {

    public static void setDrawable (View view, EditText editText, int id,String colorName) {
        //连续的字符串，长度不可变
        SpannableString ss = new SpannableString(" "+"  "+colorName);
        //得到要显示的图片资源
        Drawable d = view.getResources().getDrawable(id);
        //设置图片宽高
        d.setBounds(0, 0, 60, 60);
        //跨度底部应与周围文本的基线对齐
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        //添加图片
        ss.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // getEditableText().insert(getSelectionStart(), ss);
        editText.setText(ss);
        editText.setSelection(ss.length());
    }

}
