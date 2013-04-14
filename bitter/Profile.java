package bitter;

/**
 * A simple data transfer object that represents a user's profile in the Bitter
 * system.
 */
public class Profile {

/*
    private static final FIRSTNAME_MAX_LENGTH = 10;
    private static final LASTNAME_MAX_LENGTH = 20;
    private static final BIO_MAX_LENGTH = 500;
    private static final EMAIL_MAX_LENGTH = 50;
*/   
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public String bio;
    
    public Profile(String username) {
        this.username = username;
        firstname = null;
        lastname = null;
        email = null;
        bio = null;
    }
/*
    public getUsername() {
        return username;
    }

    public getFirstName() {
        return firstname;
    }

    public getLastName() {
        return lastname;
    }

    public getEmail() {
        return email;
    }

    public getBio() {
        return bio;
    }

    public setFirstName(String firstname) {
        if (firstname.length() > FIRSTNAME_MAX_LENGTH)
            throw IllegalArgumentException("Name too long");
        this.firstname = firstname;
    }

    public setLastName(String lastname) {
        if (lastname.length() > LASTNAME_MAX_LENGTH)
            throw IllegalArgumentException("Name too long");
        this.lastname = lastname;
    }

    public setEmail(String lastname) {
        if (email.length() > EMAIL_MAX_LENGTH)
            throw IllegalArgumentException("Name too long");
        this.email = lastname;
    }

    public setBio(String bio) {
        if (bio.length() > BIO_MAX_LENGTH)
            throw IllegalArgumentException("Name too long");
        this.bio = bio;
    }
*/
} // End class Profile
