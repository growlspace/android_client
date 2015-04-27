package space.growl.android.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nicholas on 4/26/2015.
 */
public class User implements Serializable {
    String id;
    String username;
    String bio;
    String profilePicture;
    Date created;

    public User(String id, String username, String bio, String profilePicture, Date created) {
        this.id = id;
        this.username = username;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public Date getCreated() {
        return created;
    }
}
