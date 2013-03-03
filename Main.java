import java.sql.*;
import javax.sql.*;
import java.util.*;

public class Main {

    public enum Action {
        DISPLAY, INSERT, UPDATE, DELETE, UNKNOWN
    }


    Map<Action,SQLFunction> translate;

    private void createTranslateLinks() {
        translate = new HashMap<Action, SQLFunction>();

        translate.put( Action.DISPLAY, new Display());
    }


    public Action prompt() {
        return Action.UNKNOWN;
    }

    private void run() {
        Action userChoice = prompt();


    }

    public static void main(String args[]) {
        Main app = new Main();
        app.run();
    }

}
