import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class BancoDeDados {
    public static void main(String[] args) {
        try(Connection conexao = Conexao.conectar();
        Statement comando = conexao.createStatement()){

        comando.execute(
            "IF NOT EXISTS (SELECT * FROM sys.database WHERE name = 'teste') " +
            "CREATE DATABASE teste"
        );

        JOptionPane.showMessageDialog(null, "Banco 'teste' verificado/criado com sucesso!");

        comando.execute("USE teste");

        comando.execute(
            "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Personagem' AND xtype='U'" +
            "CREATE TABLE Personagem (" + 
            "   id INT IDENTITY PRIMARY KEY, " +
            "   nome VARCHAR(50) NOT NULL, " +
            "   categoria VARCHAR(50) NOT NULL, " +
            "   valor INT NOT NULL, " +
            "   dataCriacao DATETIME DEAFAULT GETDATE()" +
            ")"
        );

        JOptionPane.showMessageDialog(null, "Tabela do 'Personagem' verificada/criada com sucesso!");

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao criar banco/tabela: ");
            e.printStackTrace();
        }
        
    }
}
