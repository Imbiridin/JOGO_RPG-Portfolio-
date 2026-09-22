import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

        private static final String url = "jdbc:sqlserver://DESKTOP-09V9M6S\\SQLEXPRESS;encrypt=true;trustServerCertificate=true;";
        private static final String user = "javauser";
        private static final String password = "123456";

        private static Connection conectar() throws SQLException {
            return DriverMenager.getConnection(url, user, password);
        }

    }

