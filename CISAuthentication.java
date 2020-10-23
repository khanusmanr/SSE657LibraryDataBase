package cisapp;

public class CISAuthentication {
    public static boolean dummyAuthenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("bob") && password.equals("secret")) {
            return true;
        }
        return false;
    }


}
