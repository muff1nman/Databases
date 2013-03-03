import java.sql.*;
import javax.sql.*;
import java.util.*;

class Display implements SQLFunction {
    public static String query = "SELECT * FROM albums";
    public void doSQL( Connection db ) throws SQLException {
        System.out.println("Display Start");
        PreparedStatement pstmt = db.prepareStatement( query );
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        int numColumns = md.getColumnCount();

        System.out.println("Number of columns: " + numColumns );

        for( int i = 1; i <= numColumns; ++i ) {
            System.out.print( md.getColumnLabel(i) + "|");
        }
        System.out.println();

        System.out.println("Display End");
    }
}

