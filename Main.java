import java.sql.*;
import javax.sql.*;
import java.util.*;

public class Main {

    private Map<Action,SQLFunction> translate;
    private Scanner in;

    public Main() {
        this.createTranslateLinks();
        this.in = new Scanner( System.in );
    }

    public enum Action {
        DISPLAY(1, "Display"), INSERT(2,"Insert"), UPDATE(3,"Update"), DELETE(4,"Delete");

        private String text;
        private int id;
        private static Map<Integer,Action> translate = null;

        private Action(int id, String text) {
            this.text = text;
            this.id = id;
        }

        public String toString() {
            return text;
        }

        public int toId() {
            return id;
        }

        public static Action getAction( int id ) {
            if ( translate == null ) {
                initTranslate();
            }

            return translate.get(id);

        }

        private static void initTranslate() {
            translate = new HashMap<Integer, Action>();
            for( Action each: Action.values() ) {
                translate.put( each.toId(), each );
            }
        }
    }

    private void createTranslateLinks() {
        translate = new HashMap<Action, SQLFunction>();

        translate.put( Action.DISPLAY, new Display());
    }


    public Action prompt() {
        System.out.println("Welcome to the Albums Database! ");
        for( Action a : Action.values() ){
            System.out.println( a.toId() + ". " + a.toString() );
        }
        System.out.println();
        System.out.print("Enter a command: ");
        return Action.getAction(
                this.in.nextInt());
    }

    private void run() {
        Action userChoice = prompt();
        if ( translate.containsKey( userChoice )) {
            this.translate.get( userChoice ).doSQL();
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
