import java.sql.*;
import javax.sql.*;
import java.util.*;

class Update implements SQLFunction {
    public static String query = "UPDATE albums set  title = ?, year = ?, rank = ? WHERE id = ?;";

    private Map<String,String> emptyValues() {
        Map<String,String> values = new HashMap<String,String>();
        values.put("title","");
        values.put("year","");
        values.put("rank","");
        return values;
    }

    private Map<String, String> promptValues() {
        Map<String,String> values = emptyValues();
        Scanner in = new Scanner(System.in);
        for( String key : values.keySet() ){
            System.out.print("New" + " " + key + ": ");
            values.put(key, in.nextLine());
        }

        System.out.println();

        return values;

    }

    public int getId() {
        Scanner in = new Scanner(System.in);
        System.out.print("Id: ");
        return in.nextInt();
    }

    public void doSQL( Connection db ) throws SQLException {
        try {
            System.out.println("Update Start");
            PreparedStatement pstmt = db.prepareStatement( query );
            System.out.println();
            System.out.println("Update Album");
            int id = getId();
            // todo print out old album
            Map<String,String> values = promptValues();
            pstmt.setString(1,values.get("title"));
            pstmt.setInt(2,Integer.parseInt(values.get("year")));
            pstmt.setInt(3,Integer.parseInt(values.get("rank")));
            pstmt.setInt(4,id);
            pstmt.executeUpdate();
            //pstmt.clearParameters();
            System.out.println("Update End");

            db.commit();

        } catch( NumberFormatException e) {
            throw new SQLException();
        }
    }


}

