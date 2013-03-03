import java.sql.*;
import javax.sql.*;
import java.util.*;

public class Main {

    private Map<Action,SQLFunction> translate;

    public Main() {
        this.createTranslateLinks();
    }

    public enum Action {
        DISPLAY(1, "Display"), INSERT(2,"Insert"), UPDATE(3,"Update"), DELETE(4,"Delete");

        String text;
        int id;

        private Action(int id, String text) {
            this.text = text;
            this.id = id;
        }

        public String toString() {
            return text;
        }
    }

    private void createTranslateLinks() {
        translate = new HashMap<Action, SQLFunction>();

        translate.put( Action.DISPLAY, new Display());
    }


    public Action prompt() {
        System.out.println("Welcome to the Albums Database! ");
        for( Action a : Action.values() ){
            System.out.println( a.toString() );
        }
        System.out.print("Enter a command: ");
        return Action.DELETE;
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
