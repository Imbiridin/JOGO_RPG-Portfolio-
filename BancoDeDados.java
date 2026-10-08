import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BancoDeDados {
    public static void main(String[] args) {
        try (Connection conexao = Conexao.conectar();
             Statement comando = conexao.createStatement()) {

            comando.execute(
                "IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'teste') " +
                "CREATE DATABASE teste"
            );
            JOptionPane.showMessageDialog(null, "Banco 'teste' verificado/criado com sucesso!");

            comando.execute("USE teste");

            comando.execute(
                "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Personagem' AND xtype='U') " +
                "CREATE TABLE Personagem (" +
                "   id INT IDENTITY PRIMARY KEY, " +
                "   nome VARCHAR(50) NOT NULL, " +
                "   categoria VARCHAR(50) NOT NULL, " +
                "   valor INT NOT NULL, " +
                "   dataCriacao DATETIME DEFAULT GETDATE()" +
                ")"
            );
            JOptionPane.showMessageDialog(null, "Tabela 'Personagem' verificada/criada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar banco/tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
}