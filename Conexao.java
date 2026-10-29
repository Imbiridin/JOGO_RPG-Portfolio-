import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "admin";
    private static final String PASSWORD = "13000";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(BASE_URL, USER, PASSWORD);
    }

    public static Connection conectar(String banco) throws SQLException {
        String url = BASE_URL + banco + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        return DriverManager.getConnection(url, USER, PASSWORD);
    }
}