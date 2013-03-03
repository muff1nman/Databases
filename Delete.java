import java.sql.*;
import javax.sql.*;
import java.util.*;

class Delete implements SQLFunction {
    public static String query = "DELETE FROM albums WHERE id = ?";

    private Map<String,String> emptyValues() {
        Map<String,String> values = new HashMap<String,String>();
        values.put("id","");
        return values;
    }

    private Map<String, String> promptValues() {
        Map<String,String> values = emptyValues();
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("Delete Album");
        for( String key : values.keySet() ){
            System.out.print(key + ": ");
            values.put(key, in.next());
        }

        System.out.println();

        return values;

    }

    public void doSQL( Connection db ) throws SQLException {
        System.out.println("Delete Start");
        PreparedStatement pstmt = db.prepareStatement( query );
        Map<String,String> values = promptValues();
        pstmt.setInt(1,Integer.parseInt(values.get("id")));
        pstmt.executeUpdate();
        //pstmt.clearParameters();
        System.out.println("Delte End");

        db.commit();
        System.out.println("Album delted successfully");
    }


}

