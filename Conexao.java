import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String BASE_URL =
        "jdbc:sqlserver://DESKTOP-09V9M6S\\SQLEXPRESS;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "javauser";
    private static final String PASSWORD = "123456";


    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(BASE_URL, USER, PASSWORD);
    }

    public static Connection conectar(String banco) throws SQLException {
        return DriverManager.getConnection(BASE_URL + "databaseName=" + banco + ";", USER, PASSWORD);
    }
}