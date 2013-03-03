import java.sql.*;
import javax.sql.*;
import java.util.*;

public class Main {

    private Map<Action,SQLFunction> translate;

    public Main() {
        this.createTranslateLinks();
    }

    public enum Action {
        DISPLAY, INSERT, UPDATE, DELETE, UNKNOWN
    }



    private void createTranslateLinks() {
        translate = new HashMap<Action, SQLFunction>();

        translate.put( Action.DISPLAY, new Display());
    }


    public Action prompt() {
        return Action.UNKNOWN;
    }

    private void run() {
        Action userChoice = prompt();
        if ( translate.containsKey( userChoice )) {
            // Run the query
        } else {
            System.out.println("Action Not Available");
            System.out.println("Exit");
        }
    }

    public static void main(String args[]) {
        Main app = new Main();
        app.run();
    }

}
