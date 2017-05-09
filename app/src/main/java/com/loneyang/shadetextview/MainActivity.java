package com.loneyang.shadetextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private ShadeTextView shadeTextView;
    private SeekBar seekBar;
    private SeekBar seekBar_right;
    private SeekBar seekBar_top;
    private SeekBar seekBar_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        shadeTextView.setmProgress(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shadeTextView.setDirection(ShadeTextView.DIRECTION_LEFT);
                shadeTextView.setmProgress(progress * 1.0f / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar_right.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shadeTextView.setDirection(ShadeTextView.DIRECTION_RIGHT);
                shadeTextView.setmProgress(progress * 1.0f / 100);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_top.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shadeTextView.setDirection(ShadeTextView.DIRECTION_TOP);
                shadeTextView.setmProgress(progress * 1.0f / 100);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_bottom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shadeTextView.setDirection(ShadeTextView.DIRECTION_BOTTOM);
                shadeTextView.setmProgress(progress * 1.0f / 100);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void initView() {
        shadeTextView = (ShadeTextView) findViewById(R.id.shadeTextView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar_right = (SeekBar) findViewById(R.id.seekBar_right);
        seekBar_top = (SeekBar) findViewById(R.id.seekBar_top);
        seekBar_bottom = (SeekBar) findViewById(R.id.seekBar_bottom);
    }
}
