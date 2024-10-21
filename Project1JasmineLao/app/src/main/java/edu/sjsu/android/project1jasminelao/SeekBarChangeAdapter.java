package edu.sjsu.android.project1jasminelao;

import android.annotation.SuppressLint;
import android.widget.SeekBar;

public interface SeekBarChangeAdapter extends SeekBar.OnSeekBarChangeListener {
    @Override
    public default void onStopTrackingTouch(SeekBar seekBar){

    }

    @Override
    public default void onStartTrackingTouch(SeekBar seekBar){

    }
}
