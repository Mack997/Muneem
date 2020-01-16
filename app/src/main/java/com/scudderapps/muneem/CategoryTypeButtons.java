package com.scudderapps.muneem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.widget.AppCompatRadioButton;

public class CategoryTypeButtons extends AppCompatRadioButton {
    private OnCheckedChangeListener onCheckedChangeListener;

    public CategoryTypeButtons(Context context) {
        super(context);
    }

    public CategoryTypeButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryTypeButtons(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setCustomCheckedListener();
    }

    public void setCustomCheckedListener(OnCheckedChangeListener onCheckedChangeListener){
            this.onCheckedChangeListener = onCheckedChangeListener;

    }

    private void setCustomCheckedListener(){
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
                }
            }
        });

    }
}
