import java.sql.*;
import javax.sql.*;
import java.util.*;

class Update extends SQLFunction {
    public static String query = "UPDATE albums set  title = ?, year = ?, rank = ? WHERE id = ?;";

    public void doSQL( Connection db ) throws SQLException {
        System.out.println("Update Start");
        PreparedStatement pstmt = db.prepareStatement( query );
        System.out.println();
        System.out.println("Update Album");
        int id = promptInt("ID: ");
        Display display = new Display();
        display.doSQL(db, id );
        pstmt.setString(1,promptString("Title: "));
        pstmt.setInt(2,promptInt("Year: "));
        pstmt.setInt(3,promptInt("Rank: "));
        pstmt.setInt(4,id);
        pstmt.executeUpdate();
        pstmt.clearParameters();
        System.out.println("Update End");

        db.commit();

    }


}

