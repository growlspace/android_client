package space.growl.android.media;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Nicholas on 4/27/2015.
 */
public class AudioPlayer {
    private static final String LOG_TAG = "AudioPlayer";

    MediaPlayer mPlayer;

    public AudioPlayer() {}

    public void startPlaying(String pFileName) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(pFileName);
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

    private class PlaybackCallbackListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.stop();
            mp.reset();
            mp.release();
        }
    }
}
