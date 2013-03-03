import java.sql.*;
import javax.sql.*;
import java.util.*;

interface SQLFunction {
    public void doSQL(Connection db );
}
