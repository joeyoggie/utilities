package com.joey.utilities;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewWithArabicDigits extends AppCompatTextView {
    public TextViewWithArabicDigits(Context context) {
        super(context);
    }

    public TextViewWithArabicDigits(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(replaceArabicNumbers(text), type);
    }

    private String replaceArabicNumbers(CharSequence original) {
        if (original != null) {
            return original.toString().replaceAll("0", "٠")
                    .replaceAll("1", "١")
                    .replaceAll("2","٢")
                    .replaceAll("3","٣")
                    .replaceAll("4","٤")
                    .replaceAll("5","٥")
                    .replaceAll("6","٦")
                    .replaceAll("7","٧")
                    .replaceAll("8","٨")
                    .replaceAll("9","٩");
        }

        return null;
    }
}