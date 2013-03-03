import java.sql.*;
import javax.sql.*;
import java.util.*;

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

