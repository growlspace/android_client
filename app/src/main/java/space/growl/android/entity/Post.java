package space.growl.android.entity;

import java.io.Serializable;

/**
 * Created by Nicholas on 4/26/2015.
 */
public class Post implements Serializable {
    public static final String AUDIO_FILE_PATH = "path";
    public static final String CAPTION = "caption";
    String id;
    User user;
    String caption;
    String imagePath;
    String audioPath;

    public Post(String audioPath, String caption) {
        this.caption = caption;
        this.audioPath = audioPath;
    }

    public Post(String id, User user, String caption, String audioPath) {
        this.id = id;
        this.user = user;
        this.caption = caption;
        this.audioPath = audioPath;
    }

    public String getUsername() {
        return user.getName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCaption() {
        return caption;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAudioPath() {
        return audioPath;
    }

}
