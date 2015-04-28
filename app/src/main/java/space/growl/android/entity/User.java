package space.growl.android.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Nicholas on 4/26/2015.
 */
public class User implements Serializable {
    String id;
    String username;
    String email;
    String bio;
    String profilePicture;
    Date created;

    public User() {
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return getUsername();
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nUser: ");
        builder.append(getUsername());
        builder.append("\nEmail: ");
        builder.append(getEmail());
        builder.append("\nBio: ");
        builder.append(getBio());
        builder.append("\nBirthday: ");
        builder.append(DateFormat.getDateInstance().format(getCreated()));
        builder.append("\n");
        return builder.toString();
    }
}
