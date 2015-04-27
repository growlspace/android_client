package com.growlspace.growlspace.media;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Nicholas on 4/27/2015.
 */
public class AudioRecorder {
    private static final String LOG_TAG = "AudioRecorder";

    private String mFileName = null;

    private MediaRecorder mRecorder = null;

    public AudioRecorder(Context context, String pFilename) {
        mFileName = context.getCacheDir().getAbsolutePath();
        mFileName += "/" + pFilename + ".3gp";
        Log.d(LOG_TAG, "Saving to " + mFileName);
    }

    public void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }


    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        mRecorder.setMaxDuration(1000);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.reset();
        mRecorder.release();
        mRecorder = null;
    }

    public void onPause() {
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }

    public void logDebug (String msg) {
        Log.d(LOG_TAG, msg);
    }
}
