import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BancoDeDados {
    public static void main(String[] args) {
        try (Connection conexao = Conexao.conectar("teste");
             Statement comando = conexao.createStatement()) {

            comando.execute(
                "CREATE TABLE IF NOT EXISTS Personagem (" +
                "   id INT AUTO_INCREMENT PRIMARY KEY, " +
                "   nome VARCHAR(50) NOT NULL, " +
                "   categoria VARCHAR(50) NOT NULL, " +
                "   valor INT NOT NULL, " +
                "   dataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ")"
            );
            JOptionPane.showMessageDialog(null, "Tabela 'Personagem' verificada/criada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar tabela: " + e.getMessage());
            e.printStackTrace();
        }
    }
}