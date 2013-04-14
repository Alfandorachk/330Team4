package bitter;

/**
 * Encapsulates the information for a user.  Not much right now, really.  Could
 * probably do without, what with all the hashes we're using.  Username is just
 * a key with our current system, isn't it?
 */
public class User {

    private String username;
    private Profile profile;

    /**
     * Creates a new user with the given name and an empty profile.
     */
    public User(String username) {
        this.username = username;
        profile = new Profile();
    }

    /**
     * Returns the user's bitter handle.
     */
    public getName() {
        return username;
    }

    /**
     * Returns the user's profile.
     */
    public getProfile() {
        return profile;
    }

}
