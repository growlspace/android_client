package space.growl.android.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Nicholas on 4/27/2015.
 */
public class AudioRecorder {
    private static final String LOG_TAG = "AudioRecorder";

    private Context mContext = null;
    private String mFileName = null;

    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;

    public AudioRecorder(Context context, String pFilename) {
        mFileName = context.getCacheDir().getAbsolutePath();
        mFileName += "/" + pFilename + ".3gp";
        mContext = context;
        Log.d(LOG_TAG, "Saving to " + mFileName);
    }

    public void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    public void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.setOnCompletionListener(new PlaybackCallbackListener());
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }


    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        mRecorder.setMaxDuration(1000);
        mRecorder.setOnInfoListener(new RecordCallbackListener());

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
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    private class RecordCallbackListener implements MediaRecorder.OnInfoListener {

        @Override
        public void onInfo(MediaRecorder mr, int what, int extra) {
            if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                onRecord(false);
                Log.d(LOG_TAG, "Recording stopped, max duration reached");
                Toast alert = Toast.makeText(mContext, "Recording complete!", Toast.LENGTH_SHORT);
                alert.show();
            }
        }
    }

    private class PlaybackCallbackListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.stop();
            mp.reset();
        }
    }

    public String getFileName() {
        return mFileName;
    }
}
