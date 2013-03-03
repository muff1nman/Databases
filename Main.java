import java.sql.*;
import javax.sql.*;
import java.util.*;

public class Main {

    private Map<Action,SQLFunction> translate;
    private Scanner in;
    private Driver d;
    private Connection conn;

    public Main() {
        this.createTranslateLinks();
        this.in = new Scanner( System.in );
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
            //this.translate.get( userChoice ).doSQL();
        } else {
            System.out.println("Action Not Available");
            System.out.println("Exit");
        }
    }

    private void initConn() {
    }

    private void finishConn() {

    }

    public static void main(String args[]) {
        Main app = new Main();
        app.run();
    }

}
