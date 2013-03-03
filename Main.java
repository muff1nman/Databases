import java.sql.*;
import javax.sql.*;
import java.util.*;

public class Main {

    private Map<Action,SQLFunction> translate;
    private Scanner in;
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

    private void initConn() throws SQLException {
        try { 
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:albums.sqlite3.db");
        } catch (Exception e ) {
            throw new SQLException( e.getMessage() );
        }
    }

    private void finishConn() {
        try {
            conn.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main app = new Main();
        app.run();
    }

}
