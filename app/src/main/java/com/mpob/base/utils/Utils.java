package com.mpob.base.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by HOLV on 11,November,2017
 * My Parents On Board,
 * Santa Monica California.
 */

public class Utils {

    public static TextView convertToLink(TextView textView) {

        CharSequence text = textView.getText();
        SpannableString spannableString = new SpannableString( text );
        spannableString.setSpan(new URLSpan(""), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);

        return textView;
    }

    public static String getETData(EditText data) {
        return data.getText().toString();
    }

}
