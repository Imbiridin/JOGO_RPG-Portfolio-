import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ColetarDados {

    public static void main(String[] args) throws Exception {
        String nomePersonagem = JOptionPane.showInputDialog("Digite o nome do persoangem: ");
        String[] categorias = {"Inteligencia", "Força", "Velocidade", "Resistencia", "Habilidade"};

        String url = "jdbc:sqlserver://DESKTOP-09V9M6S\\SQLEXPRESS;databaseName=teste;encrypt=true;trustServerCertificate=true;";
        String user = "javauser";
        String password = "123456";
        String sql = "INSERT INTO Personagem (nome, categoria, valor) VALUES(?,?,?)";

        try (Connection conexao = java.sql.DriverManager.getConnection(url, user, password)) {

            for (String categoria : categorias) {
                String valorTexto = JOptionPane.showInputDialog("Digite o valor para " + categoria + ": ");
                int valor = Integer.parseInt(valorTexto);

                  
                    try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                    stmt.setString(1, nomePersonagem);
                    stmt.setString(2, categoria);
                    stmt.setInt(3, valor);
                    stmt.executeUpadate();
                }
            }
        JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
          
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: ");
            e.printStackTrace();
    }

        String valoresFinal = valores.substring(0, valores.length() - 1); // Remove the last newline character

        ProcessBuilder pb = new ProcessBuilder("python3", "grafico.py", valoresFinal);
        pb.inheritIO();
        Process processo = pb.start();
        processo.waitFor();

        JOptionPane.showMessageDialog(null, "Valores coletados e gráfico gerado com sucesso!");

    }   
}
