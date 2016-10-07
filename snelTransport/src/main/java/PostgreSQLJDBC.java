import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class PostgreSQLJDBC {
   public static void main(String args[]) {
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/snelTransportPower",
            "transport-user", "admin");
         Statement st = c.createStatement();
          ResultSet rs = st.executeQuery("SELECT * FROM public.\"Customer\"");
          
          ResultSetMetaData rsmd = rs.getMetaData();
          System.out.println(rsmd.getColumnCount());
         
          while(rs.next()){
              System.out.println(rsmd.getColumnName(1));
              System.out.println("--------------------------");
              System.out.println(rs.getString("Name"));
          }
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }
}