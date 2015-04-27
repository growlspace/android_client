package com.growlspace.growlspace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.growlspace.growlspace.entity.Post;
import com.growlspace.growlspace.media.AudioRecorder;

import java.util.UUID;


public class ComposePostActivity extends AppCompatActivity {
    private static final String LOG_TAG = "ComposeActivity";

    AudioRecorder audioRecorder = null;

    UUID currentPostID = UUID.randomUUID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_post);
        audioRecorder = new AudioRecorder(this, currentPostID.toString());

        // set up record button
        ImageButton recordButton = (ImageButton) findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecorder.onRecord(true);
            }
        });

        // set up preview button
        ImageButton previewButton = (ImageButton) findViewById(R.id.previewButton);
        previewButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecorder.onPlay(true);
            }
        });


        // set up post submission button
        Button submitButton = (Button) findViewById(R.id.submitPostButton);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String caption = ((EditText) findViewById(R.id.postBodyEditText)).getText().toString();
                String audioFilePath = audioRecorder.getFileName();

                Intent resultIntent = new Intent();
                resultIntent.putExtra(Post.AUDIO_FILE_PATH, audioFilePath);
                resultIntent.putExtra(Post.CAPTION, caption);

                Activity parentActivity = (Activity) v.getContext();

                parentActivity.setResult(Activity.RESULT_OK, resultIntent);
                parentActivity.finish();
            }
        });
    }

    @Override
    protected void onPause() {
        audioRecorder.onPause();
        super.onPause();
    }
}
