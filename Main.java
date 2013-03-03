import java.sql.*;
import javax.sql.*;

public class Main {

    public static void main(String args[]) {
        Main app = new Main();
        app.run();
    }

    private void run() {
        System.out.println("Rolling Stone's Supposed Top 100 Albums of All Time");
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:albums.sqlite3.db");
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM albums;");

            while (results.next()) {
                System.out.println( 
                        results.getInt("rank") + " " +
                        results.getString("title") + " (" +
                        results.getInt("year") + ")");
            }

            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQLite JDBC Driver.");
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error.");
            System.out.println(e.getMessage());
        }
    }

}
