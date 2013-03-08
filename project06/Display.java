import java.sql.*;
import javax.sql.*;
import java.util.*;

class Display extends SQLFunction {
    public static String query = "SELECT * FROM albums";
    public void doSQL( Connection db, Integer id ) throws SQLException {
        String whereQuery = query + " WHERE id = ?";
        System.out.println("Display Start");
        PreparedStatement pstmt = db.prepareStatement( whereQuery );
        pstmt.setInt( 1, id );
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        int numColumns = md.getColumnCount();

        System.out.println("Number of columns: " + numColumns );

        // Print column headers
        for( int i = 1; i <= numColumns; ++i ) {
            System.out.print( md.getColumnLabel(i) + "|");
        }
        System.out.println();

        // print rows
        while ( rs.next() ) {
            for( int i = 1; i <= numColumns; ++i ) {
                System.out.print(rs.getString(i) + "|");
            }
            System.out.println();
        }

        rs.close();
        System.out.println("Display End");
    }
    public void doSQL( Connection db ) throws SQLException {
        System.out.println("Display Start");
        PreparedStatement pstmt = db.prepareStatement( query );
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        int numColumns = md.getColumnCount();

        System.out.println("Number of columns: " + numColumns );

        // Print column headers
        for( int i = 1; i <= numColumns; ++i ) {
            System.out.print( md.getColumnLabel(i) + "|");
        }
        System.out.println();

        // print rows
        while ( rs.next() ) {
            for( int i = 1; i <= numColumns; ++i ) {
                System.out.print(rs.getString(i) + "|");
            }
            System.out.println();
        }

        rs.close();
        System.out.println("Display End");
    }
}

