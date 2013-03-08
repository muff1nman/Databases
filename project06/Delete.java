import java.sql.*;
import javax.sql.*;
import java.util.*;

class Delete extends SQLFunction {
    public static String query = "DELETE FROM albums WHERE id = ?";

    public void doSQL( Connection db ) throws SQLException {
        PreparedStatement pstmt = db.prepareStatement( query );
        pstmt.setInt(1,promptInt("ID: "));
        pstmt.executeUpdate();
        pstmt.clearParameters();

        db.commit();
        System.out.println("Album delted successfully");
    }


}

