import java.sql.*;
import javax.sql.*;
import java.util.*;

class Delete extends SQLFunction {
    public static String query = "DELETE FROM albums WHERE id = ?";

    public void doSQL( Connection db ) throws SQLException {
        System.out.println("Delete Start");
        PreparedStatement pstmt = db.prepareStatement( query );
        pstmt.setInt(1,promptInt("ID: "));
        pstmt.executeUpdate();
        pstmt.clearParameters();
        System.out.println("Delte End");

        db.commit();
        System.out.println("Album delted successfully");
    }


}

