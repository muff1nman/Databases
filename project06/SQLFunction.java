import java.sql.*;
import javax.sql.*;
import java.util.*;

abstract class SQLFunction {
    abstract public void doSQL(Connection db ) throws SQLException;

    protected String promptString(String prompt) {
        Scanner in = new Scanner( System.in );
        System.out.print(prompt);
        return in.nextLine();
    }

    protected Integer promptInt(String prompt) {
        try {
            return Integer.parseInt( promptString( prompt ));
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer value.");
            return promptInt( prompt );
        }
    }
}
