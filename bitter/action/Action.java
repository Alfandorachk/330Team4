package bitter.action;

/**
 * Interface for actions that can be performed by the user on a Bitter system.
 * Defines one method, doAction(), that performs the action and formats
 * a String that will tell the user the result of that action.  If any
 * parameters are required, they need to be passed to the Action in some other
 * way, probably as constructor arguments.
 */
public interface Action {

    /**
     * Performs the action and returns a string that is to be sent to the user.
     *
     * @return a string that tells a user the result of their action
     */
    public String doAction();

}
