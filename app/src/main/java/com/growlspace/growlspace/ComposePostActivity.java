package com.growlspace.growlspace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.growlspace.growlspace.media.AudioRecorder;

import java.util.UUID;


public class ComposePostActivity extends AppCompatActivity {

    AudioRecorder audioRecorder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_post);
        audioRecorder = new AudioRecorder(this, UUID.randomUUID().toString());

        OnClickListener clicker = new OnClickListener() {
            boolean mStartRecording = true;
            @Override
            public void onClick(View v) {
                audioRecorder.onRecord(mStartRecording);
                if (mStartRecording) {
                    audioRecorder.logDebug("Started recording");
                } else {
                    audioRecorder.logDebug("Stopped recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        ImageButton recordButton = (ImageButton) findViewById(R.id.recordButton);
        recordButton.setOnClickListener(clicker);
    }

    @Override
    protected void onPause() {
        audioRecorder.onPause();
        super.onPause();
    }
}
