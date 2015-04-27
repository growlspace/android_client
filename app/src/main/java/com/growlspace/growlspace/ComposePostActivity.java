package com.growlspace.growlspace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.growlspace.growlspace.entity.Post;
import com.growlspace.growlspace.media.AudioRecorder;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

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

        OnClickListener recordClicker = new OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecorder.onRecord(true);
            }
        };

        ImageButton recordButton = (ImageButton) findViewById(R.id.recordButton);
        recordButton.setOnClickListener(recordClicker);

        Button submitButton = (Button) findViewById(R.id.submitPostButton);
        OnClickListener submitClicker = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String caption =  ((EditText) findViewById(R.id.postBodyEditText)).getText().toString();
                String audioFilePath = audioRecorder.getFileName();
                Post post = new Post(audioFilePath, caption);

                Ion.with(v.getContext())
                .load("https://koush.clockworkmod.com/test/echo")
                        .setJsonPojoBody(post)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (e != null) {
                                    Log.e(LOG_TAG, e.toString());
                                }
                                Log.d(LOG_TAG, result.toString());
                            }
                        });
                Toast.makeText(v.getContext(), "This will post this message one day.", Toast.LENGTH_SHORT).show();
            }
        };
        submitButton.setOnClickListener(submitClicker);
    }

    @Override
    protected void onPause() {
        audioRecorder.onPause();
        super.onPause();
    }
}
