package bitter;

/**
 * Encapsulates the information for a user.  Not much right now, really.  Could
 * probably do without, what with all the hashes we're using.  Username is just
 * a key with our current system, isn't it?
 */

import java.util.*;

public class User {

    private String username;
    private Profile profile;
    private ArrayList<User> subTo;

    /**
     * Creates a new user with the given name and an empty profile.
     */
    public User(String username) {
        this.username = username;
        profile = new Profile();
        subTo = new ArrayList<User>();
        
    }

    /**
     * Returns the user's bitter handle.
     */
    public String getName() {
        return username;
    }

    /**
     * Returns the user's profile.
     */
    public Profile getProfile() {
        return profile;
    }
    
    /**
     * Adds a username to subTo.
     */
    public void addSub (User name){
        if(subTo.contains(name))
            return;
        subTo.add(name);
    }

}
