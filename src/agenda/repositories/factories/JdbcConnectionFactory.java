package agenda.repositories.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionFactory {
  public static Connection getConnection() throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","3825");
    return connection;
  }
}
