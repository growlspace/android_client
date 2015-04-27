package space.growl.android.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nicholas on 4/26/2015.
 */
public class User implements Serializable {
    String username;
    String bio;
    String profilePicture;
    Date created;
}
