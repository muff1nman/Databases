import java.sql.*;
import javax.sql.*;
import java.util.*;

class Insert extends SQLFunction {
    public static String query = "INSERT INTO albums(title,year,rank) values(?,?,?);";
    public void doSQL( Connection db ) throws SQLException {
        System.out.println("Insert Start");
        PreparedStatement pstmt = db.prepareStatement( query );
        System.out.println();
        System.out.println("New Album");
        pstmt.setString(1, promptString("Title: "));
        pstmt.setInt(2, promptInt("Year: "));
        pstmt.setInt(3, promptInt("Rank: "));
        pstmt.executeUpdate();
        pstmt.clearParameters();
        System.out.println("Insert End");

        db.commit();
    }


}

