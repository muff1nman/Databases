import java.sql.*;
import javax.sql.*;
import java.util.*;

class Display implements SQLFunction {
    public void doSQL( Connection db ) throws SQLException {
        System.out.println("Displaying!");
    }
}

