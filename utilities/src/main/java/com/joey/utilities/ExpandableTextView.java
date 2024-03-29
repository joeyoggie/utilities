package com.joey.utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

public class ExpandableTextView extends AppCompatTextView {
    private int collapsedHeight;
    private int expandedHeight;
    private boolean collapsed = true;
    private int speed = 20;
    private boolean inited = false;
    private String language = "en";

    private Canvas textCanvas;

    public ExpandableTextView(Context context, String language) {
        super(context);
        this.language = language;
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inited = false;
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inited = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!inited) {
            setCollapsedLines(1);
            setMeasuredDimension(widthMeasureSpec, collapsedHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setExpandedLines(getLineCount());
        init();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    public void init(){
        if (!inited) {
            inited = true;
            this.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateState();
                }
            });

            this.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    setExpandedLines(getLineCount());
                }
            });
        }
    }
    private void updateState(){
        if (collapsed){
            expand();
        } else {
            collapse();
        }
    }

    private void expand(){
        collapsed = false;
        Utils.setTextViewEndDrawable(this, R.drawable.ic_arrow_drop_up_gray, language);
        invalidate();
    }

    private void collapse(){
        collapsed = true;
        Utils.setTextViewEndDrawable(this, R.drawable.ic_arrow_drop_down_gray, language);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if ((collapsed == false) && (getHeight() < expandedHeight)){
            setHeight(getHeight() + getSpeed() > expandedHeight ? expandedHeight : getHeight() + getSpeed());
            invalidate();
        }
        if ((collapsed) && (getHeight() > collapsedHeight)){
            setHeight(getHeight() - getSpeed() < collapsedHeight ? collapsedHeight : getHeight() - getSpeed());
            invalidate();
        }
    }

    public void setCollapsedLines(int collapsedLines) {
        collapsedHeight = (int) Math.ceil(collapsedLines * (getLineHeight() + getLineSpacingExtra()) + getPaddingBottom()  + getPaddingTop() +  getLastBaselineToBottomHeight());
    }

    private void setExpandedLines(int expandedLines) {
        expandedHeight = (int) Math.ceil(expandedLines * (getLineHeight() + getLineSpacingExtra()) + getPaddingBottom()  + getPaddingTop() + getLastBaselineToBottomHeight());
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}