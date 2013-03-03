import java.sql.*;
import javax.sql.*;
import java.util.*;

class Insert implements SQLFunction {
    public static String query = "INSERT INTO albums(title,year,rank) values(?,?,?);";
    public void doSQL( Connection db ) throws SQLException {
        System.out.println("Insert Start");
        PreparedStatement pstmt = db.prepareStatement( query );
        pstmt.clearParameters();
        System.out.println("Insert End");
    }
}

