package com.growlspace.growlspace.entity;

import java.io.Serializable;

/**
 * Created by Nicholas on 4/26/2015.
 */
public class Post implements Serializable {
    String caption;
    String imagePath;
    String audioPath; // may get changed to a context or something http://developer.android.com/reference/android/media/MediaPlayer.html

    public Post(String pAudioPath, String pCaption) {
        audioPath = pAudioPath;
        caption = pCaption;
        imagePath = null;
    }
}
